#/bin/bash

echo "Actualizando repositorio DESARROLLO WEB EN ENTORNO CLIENTE"

echo "PASO 1: Actualizar carpeta local"
git pull origin master

echo "PASO 2: Añadimos archivos locales"
git add .

echo "PASO 3: Inscripción de subida"
read -p "Escribe el mensaje de subida" mensaje
git commit -m "$mensaje"

echo "PASO 4: Realizar subida"
git push origin master
