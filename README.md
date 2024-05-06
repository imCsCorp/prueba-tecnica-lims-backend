# Prueba tercnica camilo soto

## correr mysql con docker 
```bash
docker volume create mariadb_dev
docker run -d --name mariadb --restart=always -p 3306:3306 -e MARIADB_ROOT_PASSWORD=Colombia1@ -e MARIADB_DATABASE=prueba_tecnica_db -v mariadb_dev:/var/lib/mysql mariadb
```
## Generar version

## Generar Imagen Docker 

## Correr imagen docker 
