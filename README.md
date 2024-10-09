<h1 align="center">Conversor de Monedas</h1>

![Logo del proyecto](src/img/logo.jpeg) <!-- Reemplaza 'ruta/al/logo.png' con la ubicación de tu imagen -->

## Índice
- [Descripción](#descripción)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [API de Consumo](#api-de-consumo)
- [Instalación](#instalación)
- [Uso](#uso)
- [Funciones Principales](#funciones-principales)
- [Pruebas](#pruebas)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Descripción
El proyecto **Conversor de Monedas** es una aplicación que permite a los usuarios convertir montos de dinero entre diferentes monedas utilizando tasas de cambio actualizadas. Se conecta a la API de Exchange Rate para obtener las tasas de conversión necesarias.

## Tecnologías Utilizadas
- Java 11 o superior
- Biblioteca Gson para el manejo de JSON
- HttpClient para realizar solicitudes HTTP
- IDE: IntelliJ IDEA

## API de Consumo
Este proyecto utiliza la [Exchange Rate API](https://app.exchangerate-api.com) para obtener las tasas de cambio. La URL base para realizar las solicitudes es:


Donde `<API_KEY>` debe ser reemplazado con tu clave de API. Puedes obtener tu propia clave registrándote en el sitio de Exchange Rate API. Asegúrate de no compartir tu clave de API en repositorios públicos.

## Instalación
1. Clona el repositorio en tu máquina local:
   ```bash
   git clone https://github.com/tu_usuario/conversorMonedas.git
