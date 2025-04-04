from PIL import Image, ImageTk, ImageDraw
import tkinter as tk
from tkinter import filedialog, colorchooser, ttk, messagebox
import numpy as np
from PIL import ImageChops, ImageEnhance
import colorsys
import io
import threading
import os
import sys
import logging
import json
import hashlib
import shutil
from pathlib import Path
import time

# Configurar logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

class DegradadoGIF:
    def __init__(self):
        self.ventana = tk.Tk()
        self.ventana.title("PngTo80's By MetaCodeX")
        self.ventana.geometry("800x680")
        
        self.imagen_original = None
        self.color1 = None
        self.color2 = None
        self.frames = []
        self.frames_original = []  # Nuevo buffer para frames sin escalar
        self.frame_actual = 0
        self.previsualizando = False
        self.gif_bytes = None
        self.ultima_ruta_guardado = None
        
        # Variables de control
        self.var_ciclos = tk.IntVar(value=1)
        self.var_fps = tk.IntVar(value=30)
        self.var_duracion = tk.IntVar(value=6)
        self.var_direccion = tk.StringVar(value="horizontal")
        self.var_hex_color1 = tk.StringVar(value="#000000")
        self.var_hex_color2 = tk.StringVar(value="#000000")
        self.var_paso = tk.IntVar(value=4)
        
        # Validación para hexadecimal
        self.validacion_hex = self.ventana.register(self.validar_hex)
        
        # Configurar fuente por defecto
        self.fuente_default = self._obtener_fuente_default()
        
        # Configurar sistema de caché
        self.cache_dir = self._configurar_cache()
        
        self.setup_ui()
        
    def _obtener_fuente_default(self):
        """Obtiene una fuente por defecto compatible con el sistema"""
        try:
            # Intentar cargar Arial
            return ImageDraw.truetype("arial.ttf", 12)
        except:
            try:
                # Intentar cargar DejaVu Sans
                return ImageDraw.truetype("DejaVuSans.ttf", 12)
            except:
                # Si no hay fuentes disponibles, usar la fuente por defecto
                return None
                
    def validar_hex(self, valor):
        if not valor:
            return True
        return all(c in '0123456789ABCDEFabcdef#' for c in valor) and len(valor) <= 7

    def actualizar_color_desde_hex(self, num):
        try:
            hex_valor = self.var_hex_color1.get() if num == 1 else self.var_hex_color2.get()
            if not hex_valor.startswith('#'):
                hex_valor = '#' + hex_valor
            if len(hex_valor) == 7:
                r = int(hex_valor[1:3], 16)
                g = int(hex_valor[3:5], 16)
                b = int(hex_valor[5:7], 16)
                if num == 1:
                    self.color1 = (r, g, b)
                else:
                    self.color2 = (r, g, b)
                self.frames = []
                self.gif_bytes = None
                self.btn_guardar.config(state='disabled')
        except ValueError as e:
            logger.error(f"Error al actualizar color: {e}")
            messagebox.showerror("Error", f"Color hexadecimal inválido: {hex_valor}")

    def setup_ui(self):
        # Panel principal
        panel_principal = tk.PanedWindow(self.ventana, orient=tk.HORIZONTAL)
        panel_principal.pack(fill=tk.BOTH, expand=True)
        
        # Panel izquierdo (controles)
        panel_control = tk.Frame(panel_principal, width=200)
        panel_control.pack_propagate(False)
        panel_principal.add(panel_control)
        
        # Título del panel de control
        tk.Label(panel_control, text="PngTo80's", font=('Arial', 12, 'bold')).pack(pady=2)
        tk.Label(panel_control, text="By MetaCodeX", font=('Arial', 8, 'italic')).pack(pady=1)
        
        # Frame para botones principales
        frame_botones = tk.Frame(panel_control)
        frame_botones.pack(pady=2)
        
        tk.Button(frame_botones, text="Abrir PNG", command=self.abrir_imagen).pack(fill=tk.X, padx=5, pady=1)
        
        # Frame para Color 1
        frame_color1 = tk.Frame(frame_botones)
        frame_color1.pack(fill=tk.X, padx=5, pady=1)
        tk.Button(frame_color1, text="Color 1", command=lambda: self.seleccionar_color(1)).pack(side=tk.LEFT, padx=2)
        hex_entry1 = tk.Entry(frame_color1, textvariable=self.var_hex_color1, validate='key', 
                            validatecommand=(self.validacion_hex, '%P'), width=7)
        hex_entry1.pack(side=tk.LEFT, padx=2)
        hex_entry1.bind('<Return>', lambda e: self.actualizar_color_desde_hex(1))
        hex_entry1.bind('<FocusOut>', lambda e: self.actualizar_color_desde_hex(1))
        
        # Frame para Color 2
        frame_color2 = tk.Frame(frame_botones)
        frame_color2.pack(fill=tk.X, padx=5, pady=1)
        tk.Button(frame_color2, text="Color 2", command=lambda: self.seleccionar_color(2)).pack(side=tk.LEFT, padx=2)
        hex_entry2 = tk.Entry(frame_color2, textvariable=self.var_hex_color2, validate='key',
                            validatecommand=(self.validacion_hex, '%P'), width=7)
        hex_entry2.pack(side=tk.LEFT, padx=2)
        hex_entry2.bind('<Return>', lambda e: self.actualizar_color_desde_hex(2))
        hex_entry2.bind('<FocusOut>', lambda e: self.actualizar_color_desde_hex(2))
        
        # Separador
        ttk.Separator(panel_control, orient='horizontal').pack(fill='x', pady=5)
        
        # Frame para opciones de animación
        frame_opciones = ttk.LabelFrame(panel_control, text="Opciones de Animación")
        frame_opciones.pack(fill=tk.X, padx=5, pady=5)
        
        # Tamaño del paso
        tk.Label(frame_opciones, text="Tamaño del paso (píxeles):", font=('Arial', 9)).pack(anchor='w')
        frame_paso = tk.Frame(frame_opciones)
        frame_paso.pack(fill=tk.X, pady=1)
        
        # Slider para el paso con números enteros de 1 a 8
        paso_slider = ttk.Scale(frame_paso, 
                              from_=1,
                              to=8,
                              value=4,
                              variable=self.var_paso,
                              command=self.actualizar_paso,
                              orient='horizontal')
        paso_slider.pack(side=tk.LEFT, fill=tk.X, expand=True)
        
        # Etiqueta para mostrar el valor actual
        tk.Label(frame_paso, textvariable=self.var_paso, 
                width=1, font=('Arial', 9)).pack(side=tk.LEFT, padx=5)
        
        ttk.Separator(frame_opciones, orient='horizontal').pack(fill='x', pady=5)
        
        # Ciclos
        tk.Label(frame_opciones, text="Ciclos:", font=('Arial', 9)).pack(anchor='w')
        ciclos_spin = ttk.Spinbox(frame_opciones, from_=1, to=10, textvariable=self.var_ciclos, width=8)
        ciclos_spin.pack(fill=tk.X, pady=1)
        
        # FPS
        tk.Label(frame_opciones, text="FPS:", font=('Arial', 9)).pack(anchor='w')
        fps_spin = ttk.Spinbox(frame_opciones, from_=15, to=60, textvariable=self.var_fps, width=8)
        fps_spin.pack(fill=tk.X, pady=1)
        
        # Duración
        tk.Label(frame_opciones, text="Duración (segundos):", font=('Arial', 9)).pack(anchor='w')
        duracion_spin = ttk.Spinbox(frame_opciones, from_=1, to=20, textvariable=self.var_duracion, width=8)
        duracion_spin.pack(fill=tk.X, pady=1)
        
        # Dirección del degradado
        tk.Label(frame_opciones, text="Dirección del degradado:", font=('Arial', 9)).pack(anchor='w')
        for valor, texto in [("horizontal", "Horizontal"), 
                           ("vertical", "Vertical"), 
                           ("diagonal_derecha", "Diagonal Derecha"),
                           ("diagonal_izquierda", "Diagonal Izquierda"),
                           ("central", "Central")]:
            tk.Radiobutton(frame_opciones, text=texto, variable=self.var_direccion, 
                          value=valor, font=('Arial', 9)).pack(anchor='w')
        
        # Botones de acción
        ttk.Separator(panel_control, orient='horizontal').pack(fill='x', pady=5)
        self.btn_generar = tk.Button(panel_control, text="Generar", command=self.iniciar_generacion)
        self.btn_generar.pack(fill=tk.X, padx=5, pady=1)
        self.btn_guardar = tk.Button(panel_control, text="Guardar GIF", command=self.guardar_gif, state='disabled')
        self.btn_guardar.pack(fill=tk.X, padx=5, pady=1)
        self.btn_abrir_carpeta = tk.Button(panel_control, text="Abrir Carpeta", command=self.abrir_carpeta_gif, state='disabled')
        self.btn_abrir_carpeta.pack(fill=tk.X, padx=5, pady=1)
        
        # Separador antes de la firma adicional
        ttk.Separator(panel_control, orient='horizontal').pack(fill='x', pady=5)
        
        # Frame para la firma adicional
        frame_firma = tk.Frame(panel_control)
        frame_firma.pack(fill=tk.X, padx=5, pady=5, side=tk.BOTTOM)
        
        tk.Label(frame_firma, text="Synnodex Util", 
                font=('Arial', 10, 'bold'), 
                fg='#666666').pack()
        tk.Label(frame_firma, text="Powered By MacroStasis", 
                font=('Arial', 8, 'italic'),
                fg='#888888').pack()
        
        # Panel derecho (visualización)
        panel_derecho = tk.Frame(panel_principal)
        panel_principal.add(panel_derecho)
        
        # Frame para la imagen con borde decorativo
        self.frame_imagen = tk.LabelFrame(panel_derecho, text="Vista Previa", 
                                        font=('Arial', 9, 'bold'),
                                        borderwidth=2,
                                        relief=tk.GROOVE,
                                        padx=10, pady=10)
        self.frame_imagen.pack(expand=True, fill=tk.BOTH, padx=5, pady=5)
        
        # Contenedor interno para la imagen
        frame_interno = tk.Frame(self.frame_imagen, relief=tk.SUNKEN, borderwidth=1)
        frame_interno.pack(expand=True, fill=tk.BOTH)
        
        # Label para la imagen
        self.label_imagen = tk.Label(frame_interno, bg='#f0f0f0')
        self.label_imagen.pack(expand=True, padx=2, pady=2)
        
        # Barra de progreso
        self.frame_progreso = tk.Frame(panel_derecho)
        self.frame_progreso.pack(fill=tk.X, padx=10, pady=2)
        self.barra_progreso = ttk.Progressbar(self.frame_progreso, mode='determinate')
        self.barra_progreso.pack(fill=tk.X)
        self.label_progreso = tk.Label(self.frame_progreso, text="", font=('Arial', 9))
        self.label_progreso.pack()

    def actualizar_progreso(self, valor, texto):
        self.barra_progreso['value'] = valor
        self.label_progreso.config(text=texto)
        self.ventana.update_idletasks()

    def iniciar_generacion(self):
        if not all([self.imagen_original, self.color1, self.color2]):
            messagebox.showwarning("Advertencia", "Por favor, selecciona una imagen y dos colores antes de generar.")
            return
            
        self.btn_generar.config(state='disabled')
        self.btn_guardar.config(state='disabled')
        threading.Thread(target=self.generar_con_progreso, daemon=True).start()

    def generar_con_progreso(self):
        try:
            self.actualizar_progreso(0, "Iniciando generación...")
            
            duracion_total = self.var_duracion.get()
            fps = self.var_fps.get()
            frames_totales = duracion_total * fps
            ciclos = self.var_ciclos.get()
            
            self.frames = []
            ancho, alto = self.imagen_original.size
            
            # Pre-calcular algunos valores para optimización
            paso_base = self.var_paso.get()
            paso = max(1, paso_base // 2)
            direccion = self.var_direccion.get()
            
            # Crear buffer para los frames
            frames_buffer = []
            frames_preview = []  # Buffer separado para previsualización
            
            # Calcular ratio para previsualización
            ratio = min(500/ancho, 500/alto)
            nuevo_ancho = int(ancho * ratio)
            nuevo_alto = int(alto * ratio)
            
            for i in range(frames_totales):
                progreso = (i / frames_totales) * ciclos
                degradado = self.crear_degradado(ancho, alto, self.color1, self.color2, progreso)
                frame = self.aplicar_degradado(self.imagen_original, degradado)
                
                # Guardar frame original para el GIF final
                frames_buffer.append(frame)
                
                # Crear versión escalada para previsualización
                frame_preview = frame.resize((nuevo_ancho, nuevo_alto), Image.Resampling.LANCZOS)
                frames_preview.append(frame_preview)
                
                porcentaje = (i + 1) / frames_totales * 100
                self.actualizar_progreso(porcentaje, f"Generando frames... {int(porcentaje)}%")
            
            # Guardar ambas versiones
            self.frames = frames_preview  # Para previsualización
            self.frames_original = frames_buffer  # Para guardar
            
            self.actualizar_progreso(100, "Guardando GIF en memoria...")
            self.gif_bytes = io.BytesIO()
            
            # Guardar GIF con frames originales (sin escalar)
            primera_imagen = self.frames_original[0].copy()
            primera_imagen.save(
                self.gif_bytes,
                format='GIF',
                save_all=True,
                append_images=self.frames_original[1:],
                duration=int(1000/fps),
                loop=0,
                optimize=False  # Deshabilitar optimización para mantener calidad visual
            )
            
            self.actualizar_progreso(100, "¡GIF generado! Iniciando previsualización...")
            self.ventana.after(0, self.iniciar_preview)
            self.btn_generar.config(state='normal')
            self.btn_guardar.config(state='normal')
            
        except Exception as e:
            logger.error(f"Error durante la generación: {e}")
            self.actualizar_progreso(0, f"Error: {str(e)}")
            self.btn_generar.config(state='normal')
            messagebox.showerror("Error", f"Ocurrió un error durante la generación: {str(e)}")
        
    def iniciar_preview(self):
        self.previsualizando = True
        self.frame_actual = 0
        self.btn_guardar.config(state='normal')
        self.mostrar_siguiente_frame()
        
    def mostrar_siguiente_frame(self):
        if not self.previsualizando or not self.frames:
            return
            
        self.mostrar_imagen(self.frames[self.frame_actual])
        self.frame_actual = (self.frame_actual + 1) % len(self.frames)
        
        # Calcular el tiempo de espera basado en FPS
        tiempo_espera = int(1000 / self.var_fps.get())
        self.ventana.after(tiempo_espera, self.mostrar_siguiente_frame)

    def guardar_gif(self):
        if not self.gif_bytes:
            messagebox.showwarning("Advertencia", "No hay GIF generado para guardar.")
            return
            
        ruta_guardado = filedialog.asksaveasfilename(
            defaultextension=".gif",
            filetypes=[("GIF files", "*.gif")]
        )
        if ruta_guardado:
            try:
                # Guardar el GIF desde la memoria
                with open(ruta_guardado, 'wb') as f:
                    f.write(self.gif_bytes.getvalue())
                self.ultima_ruta_guardado = ruta_guardado
                self.btn_abrir_carpeta.config(state='normal')
                self.actualizar_progreso(100, "¡GIF guardado exitosamente!")
                messagebox.showinfo("Éxito", "GIF guardado correctamente.")
            except Exception as e:
                logger.error(f"Error al guardar GIF: {e}")
                messagebox.showerror("Error", f"Error al guardar el GIF: {str(e)}")

    def abrir_imagen(self):
        ruta = filedialog.askopenfilename(filetypes=[("PNG files", "*.png")])
        if ruta:
            try:
                self.imagen_original = Image.open(ruta).convert('RGBA')
                self.mostrar_imagen(self.imagen_original)
                self.previsualizando = False
                self.frames = []
                self.gif_bytes = None
                self.btn_generar.config(state='normal')
                self.btn_guardar.config(state='disabled')
                self.actualizar_progreso(0, "")
            except Exception as e:
                logger.error(f"Error al abrir imagen: {e}")
                messagebox.showerror("Error", f"Error al abrir la imagen: {str(e)}")

    def seleccionar_color(self, num):
        color = colorchooser.askcolor(title=f"Selecciona Color {num}")[0]
        if color:
            try:
                if num == 1:
                    self.color1 = tuple(map(int, color))
                    self.var_hex_color1.set('#{:02x}{:02x}{:02x}'.format(*self.color1))
                else:
                    self.color2 = tuple(map(int, color))
                    self.var_hex_color2.set('#{:02x}{:02x}{:02x}'.format(*self.color2))
                self.frames = []
                self.gif_bytes = None
                self.btn_guardar.config(state='disabled')
            except Exception as e:
                logger.error(f"Error al seleccionar color: {e}")
                messagebox.showerror("Error", f"Error al seleccionar color: {str(e)}")
    
    def interpolar_color_optimizado(self, color1, color2, ratio):
        """Interpolación de color mejorada para transiciones más suaves"""
        # Convertir a HSV para mejor interpolación de colores
        hsv1 = colorsys.rgb_to_hsv(color1[0]/255, color1[1]/255, color1[2]/255)
        hsv2 = colorsys.rgb_to_hsv(color2[0]/255, color2[1]/255, color2[2]/255)
        
        # Interpolación suave usando curva de Hermite
        ratio = ratio * ratio * (3 - 2 * ratio)
        
        # Interpolar en espacio HSV
        h = hsv1[0] + (hsv2[0] - hsv1[0]) * ratio
        s = hsv1[1] + (hsv2[1] - hsv1[1]) * ratio
        v = hsv1[2] + (hsv2[2] - hsv1[2]) * ratio
        
        # Convertir de vuelta a RGB
        rgb = colorsys.hsv_to_rgb(h, s, v)
        return (int(rgb[0]*255), int(rgb[1]*255), int(rgb[2]*255), 255)

    def actualizar_paso(self, valor):
        """Actualiza el valor del paso sin regenerar automáticamente"""
        try:
            nuevo_paso = round(float(valor))
            # Validar que el valor esté dentro del rango esperado
            if 1 <= nuevo_paso <= 8:
                self.var_paso.set(nuevo_paso)
                logger.info(f"Paso actualizado a: {nuevo_paso}")
            else:
                logger.warning(f"Valor de paso fuera de rango: {nuevo_paso}")
        except ValueError as e:
            logger.error(f"Error al actualizar paso: {e}")

    def crear_degradado(self, ancho, alto, color1, color2, progreso):
        imagen = Image.new('RGBA', (ancho, alto), (0, 0, 0, 0))
        draw = ImageDraw.Draw(imagen)
        
        ciclo = progreso % 1.0
        paso_base = self.var_paso.get()
        # Usar un paso más pequeño para mejor calidad
        paso = max(1, paso_base // 4)
        direccion = self.var_direccion.get()
        
        # Pre-calcular colores para optimización con más niveles
        def calcular_color(ratio):
            ratio = min(1.0, max(0.0, ratio))
            return self.interpolar_color_optimizado(color1, color2, ratio)
        
        if direccion in ["diagonal_derecha", "diagonal_izquierda"]:
            # Verificar si existe en caché
            cache_key = self._generar_clave_cache(ancho, alto, color1, color2, progreso, direccion, paso)
            ruta_cache = os.path.join(self.cache_dir, cache_key + '.png')
            
            if os.path.exists(ruta_cache):
                try:
                    imagen = Image.open(ruta_cache)
                    draw = ImageDraw.Draw(imagen)
                    self._agregar_firma(draw, ancho, alto)
                    return imagen
                except Exception as e:
                    logger.error(f"Error al cargar desde caché: {e}")
            
            # Si no está en caché, crear nuevo degradado
            total_pasos = ancho + alto
            
            # Pre-calcular todos los colores posibles para evitar cálculos repetitivos
            colores_cache = {}
            
            # Crear una lista de coordenadas para procesar en lotes
            coordenadas = []
            for y in range(0, alto, paso):
                for x in range(0, ancho, paso):
                    coordenadas.append((x, y))
            
            # Procesar coordenadas en lotes para mejor rendimiento
            tamano_lote = 1000
            for i in range(0, len(coordenadas), tamano_lote):
                lote = coordenadas[i:i+tamano_lote]
                for x, y in lote:
                    pos = (x + (y if direccion == "diagonal_derecha" else alto - y)) / total_pasos
                    dist = (pos + ciclo) % 1.0
                    ratio = min(dist * 2, 2.0 - dist * 2)
                    
                    # Suavizado adicional del ratio
                    ratio = ratio * ratio * (3 - 2 * ratio)
                    
                    # Usar caché para colores con más precisión
                    ratio_redondeado = round(ratio, 4)  # Aumentar precisión
                    if ratio_redondeado in colores_cache:
                        color = colores_cache[ratio_redondeado]
                    else:
                        color = calcular_color(ratio)
                        colores_cache[ratio_redondeado] = color
                    
                    # Dibujar con anti-aliasing
                    if paso > 1:
                        # Crear un degradado suave dentro de cada rectángulo
                        for dy in range(paso):
                            for dx in range(paso):
                                sub_ratio = ratio + (dx + dy)/(paso * 4) - 0.25
                                sub_ratio = min(1.0, max(0.0, sub_ratio))
                                sub_color = calcular_color(sub_ratio)
                                if x+dx < ancho and y+dy < alto:
                                    draw.point([x+dx, y+dy], fill=sub_color)
                    else:
                        draw.point([x, y], fill=color)
            
            # Agregar firma
            self._agregar_firma(draw, ancho, alto)
            
            # Guardar en caché
            try:
                imagen_cache = imagen.copy()
                draw_cache = ImageDraw.Draw(imagen_cache)
                draw_cache.rectangle([ancho-200, alto-30, ancho, alto], fill=(0,0,0,0))
                imagen_cache.save(ruta_cache)
            except Exception as e:
                logger.error(f"Error al guardar en caché: {e}")
            
        elif direccion == "horizontal":
            for x in range(0, ancho, paso):
                pos = x / ancho
                dist = (pos + ciclo) % 1.0
                ratio = min(dist * 2, 2.0 - dist * 2)
                ratio = ratio * ratio * (3 - 2 * ratio)  # Suavizado adicional
                color = calcular_color(ratio)
                
                # Dibujar con anti-aliasing
                if paso > 1:
                    for dx in range(paso):
                        sub_ratio = ratio + dx/(paso * 2) - 0.25
                        sub_ratio = min(1.0, max(0.0, sub_ratio))
                        sub_color = calcular_color(sub_ratio)
                        if x+dx < ancho:
                            draw.line([(x+dx, 0), (x+dx, alto-1)], fill=sub_color)
                else:
                    draw.line([(x, 0), (x, alto-1)], fill=color)
            
        elif direccion == "vertical":
            for y in range(0, alto, paso):
                pos = y / alto
                dist = (pos + ciclo) % 1.0
                ratio = min(dist * 2, 2.0 - dist * 2)
                ratio = ratio * ratio * (3 - 2 * ratio)  # Suavizado adicional
                color = calcular_color(ratio)
                
                # Dibujar con anti-aliasing
                if paso > 1:
                    for dy in range(paso):
                        sub_ratio = ratio + dy/(paso * 2) - 0.25
                        sub_ratio = min(1.0, max(0.0, sub_ratio))
                        sub_color = calcular_color(sub_ratio)
                        if y+dy < alto:
                            draw.line([(0, y+dy), (ancho-1, y+dy)], fill=sub_color)
                else:
                    draw.line([(0, y), (ancho-1, y)], fill=color)
            
        else:  # central
            max_dim = max(ancho, alto)
            centro_x = ancho // 2
            centro_y = alto // 2
            
            for r in range(0, max_dim, paso):
                pos = r / max_dim
                dist = (pos + ciclo) % 1.0
                ratio = min(dist * 2, 2.0 - dist * 2)
                ratio = ratio * ratio * (3 - 2 * ratio)  # Suavizado adicional
                color = calcular_color(ratio)
                
                # Dibujar círculos concéntricos con anti-aliasing
                if paso > 1:
                    for dr in range(paso):
                        sub_ratio = ratio + dr/(paso * 2) - 0.25
                        sub_ratio = min(1.0, max(0.0, sub_ratio))
                        sub_color = calcular_color(sub_ratio)
                        draw.ellipse([centro_x-(r+dr), centro_y-(r+dr), 
                                    centro_x+(r+dr), centro_y+(r+dr)], 
                                   outline=sub_color)
                else:
                    draw.ellipse([centro_x-r, centro_y-r, centro_x+r, centro_y+r], 
                               outline=color)
        
        return imagen

    def _agregar_firma(self, draw, ancho, alto):
        """Método auxiliar para agregar la firma"""
        firma = "PngTo80's By MetaCodeX"
        font_size = max(10, min(ancho, alto) // 30)
        
        # Usar la fuente por defecto si está disponible
        font = self.fuente_default
        if font:
            try:
                font = ImageDraw.truetype("arial.ttf", font_size)
            except:
                pass
        
        if font:
            try:
                bbox = draw.textbbox((0, 0), firma, font=font)
                text_width = bbox[2] - bbox[0]
                text_height = bbox[3] - bbox[1]
            except:
                # Fallback si textbbox no está disponible
                text_width = len(firma) * font_size // 2
                text_height = font_size
        else:
            text_width = len(firma) * font_size // 2
            text_height = font_size
        
        x = ancho - text_width - 10
        y = alto - text_height - 10
        
        # Agregar sombra para mejor legibilidad
        draw.text((x+1, y+1), firma, fill=(0, 0, 0, 128), font=font)
        draw.text((x, y), firma, fill=(255, 255, 255, 255), font=font)

    def aplicar_degradado(self, imagen_base, degradado):
        """Aplica el degradado a la imagen base usando el canal alfa como máscara"""
        try:
            # Verificar que la imagen tenga canal alfa
            if imagen_base.mode != 'RGBA':
                imagen_base = imagen_base.convert('RGBA')
                
            mascara_datos = imagen_base.split()[3]
            resultado = Image.new('RGBA', imagen_base.size, (0, 0, 0, 0))
            resultado.paste(degradado, mask=mascara_datos)
            return resultado
        except Exception as e:
            logger.error(f"Error al aplicar degradado: {e}")
            # Devolver la imagen original en caso de error
            return imagen_base
    
    def mostrar_imagen(self, imagen):
        try:
            ancho_original = imagen.width
            alto_original = imagen.height
            
            # Ajustar el tamaño máximo de visualización
            ratio = min(500/ancho_original, 500/alto_original)
            nuevo_ancho = int(ancho_original * ratio)
            nuevo_alto = int(alto_original * ratio)
            
            # Solo escalar para la previsualización
            imagen_preview = imagen.resize((nuevo_ancho, nuevo_alto), Image.Resampling.LANCZOS)
            imagen_tk = ImageTk.PhotoImage(imagen_preview)
            
            self.label_imagen.config(image=imagen_tk)
            self.label_imagen.image = imagen_tk
        except Exception as e:
            logger.error(f"Error al mostrar imagen: {e}")
    
    def abrir_carpeta_gif(self):
        if self.ultima_ruta_guardado:
            try:
                import os
                import subprocess
                
                # Obtener el directorio de la última ruta de guardado
                directorio = os.path.dirname(self.ultima_ruta_guardado)
                
                # Abrir el explorador de archivos en la ubicación
                if os.name == 'nt':  # Windows
                    os.startfile(directorio)
                elif os.name == 'posix':  # macOS y Linux
                    # Intentar diferentes comandos para abrir el explorador
                    comandos = ['xdg-open', 'open', 'nautilus', 'dolphin', 'nemo']
                    for comando in comandos:
                        try:
                            subprocess.run([comando, directorio], check=True)
                            break
                        except:
                            continue
            except Exception as e:
                logger.error(f"Error al abrir carpeta: {e}")
                messagebox.showerror("Error", f"No se pudo abrir la carpeta: {str(e)}")

    def iniciar(self):
        self.ventana.mainloop()

    def _configurar_cache(self):
        """Configura el directorio de caché oculto"""
        # Obtener directorio de la aplicación
        if getattr(sys, 'frozen', False):
            # Si es un ejecutable compilado
            app_dir = os.path.dirname(sys.executable)
        else:
            # Si es un script Python
            app_dir = os.path.dirname(os.path.abspath(__file__))
        
        # Crear directorio de caché oculto
        cache_dir = os.path.join(app_dir, '.pngtocache')
        
        # Asegurar que el directorio existe y está oculto
        if not os.path.exists(cache_dir):
            os.makedirs(cache_dir)
            # Ocultar el directorio en Windows
            if os.name == 'nt':
                import ctypes
                ctypes.windll.kernel32.SetFileAttributesW(cache_dir, 0x02)  # FILE_ATTRIBUTE_HIDDEN
        
        # Crear subdirectorio para degradados
        degradados_dir = os.path.join(cache_dir, 'degradados')
        if not os.path.exists(degradados_dir):
            os.makedirs(degradados_dir)
            if os.name == 'nt':
                import ctypes
                ctypes.windll.kernel32.SetFileAttributesW(degradados_dir, 0x02)
        
        return degradados_dir
    
    def _limpiar_cache_antiguo(self, max_edad_dias=7):
        """Limpia archivos de caché más antiguos que max_edad_dias"""
        try:
            tiempo_actual = time.time()
            for archivo in os.listdir(self.cache_dir):
                ruta_archivo = os.path.join(self.cache_dir, archivo)
                if os.path.isfile(ruta_archivo):
                    # Verificar edad del archivo
                    tiempo_modificacion = os.path.getmtime(ruta_archivo)
                    edad_dias = (tiempo_actual - tiempo_modificacion) / (24 * 3600)
                    
                    if edad_dias > max_edad_dias:
                        os.remove(ruta_archivo)
                        logger.info(f"Archivo de caché eliminado: {archivo}")
        except Exception as e:
            logger.error(f"Error al limpiar caché: {e}")

    def _generar_clave_cache(self, ancho, alto, color1, color2, progreso, direccion, paso):
        """Genera una clave única para el caché basada en los parámetros del degradado"""
        # Crear una cadena con todos los parámetros
        params = f"{ancho}_{alto}_{color1}_{color2}_{progreso}_{direccion}_{paso}"
        # Generar hash MD5 para usar como nombre de archivo
        return hashlib.md5(params.encode()).hexdigest()

if __name__ == "__main__":
    app = DegradadoGIF()
    app.iniciar() 