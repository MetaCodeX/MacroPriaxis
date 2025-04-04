import PyInstaller.__main__
import sys
import os

# Configurar los argumentos para PyInstaller
args = [
    'pypng.py',  # Tu script principal
    '--onefile',  # Crear un solo archivo ejecutable
    '--noconsole',  # Sin ventana de consola
    '--name=PngTo80s',  # Nombre del ejecutable
    '--icon=icon.ico',  # Icono del ejecutable
    '--add-data=icon.ico;.',  # Incluir el icono como recurso
    # Información del programa
    '--version-file=version.txt',
    # Asegurarse de incluir todas las dependencias
    '--hidden-import=PIL',
    '--hidden-import=PIL._tkinter_finder',
    '--hidden-import=colorsys',
    '--hidden-import=numpy',
]

# Ejecutar PyInstaller
PyInstaller.__main__.run(args) 