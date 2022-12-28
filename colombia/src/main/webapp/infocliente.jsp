<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ver Cliente</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <section>
        <h1>Datos personales del Cliente</h1>
        <p>ID CLIENTE: ${c.getId()}</p>
        <p>Nombre: ${c.getNombre()}</p>
        <p>Apellido: ${c.getApellido()}</p>
        <p>Contacto: ${c.getContacto()}</p>

        <a href="index.html">Volver</a>
    </section>
</body>
</html>