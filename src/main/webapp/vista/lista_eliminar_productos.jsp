<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Productos</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>
    <body>
        <section class="section">
            <div class="container">
                <h1 class="title">Lista de Productos</h1>

                <c:if test="${not empty mensaje}">
                    <div class="notification is-danger is-light">
                        ${mensaje}
                    </div>
                </c:if>

                <table class="table is-striped is-hoverable is-fullwidth">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Precio Unitario</th>
                            <th>Stock</th>
                            <th>Categoría</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="articulo" items="${lista_de_articulos}">
                            <tr>
                                <td>${articulo.trackID}</td>
                                <td>${articulo.trackName}</td>
                                <td>${articulo.description}</td>
                                <td>${articulo.unitPrice}</td>
                                <td>${articulo.stock}</td>
                                <td>${articulo.category}</td>
                                <td>
                                    <button class="button is-danger openModalButton" data-trackid="${articulo.trackID}">Eliminar</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </section>

        <div id="myModal" class="modal">
            <div class="modal-background"></div>
            <div class="modal-content">
                <div class="box">
                    <h2 class="title">Eliminar</h2>
                    <p>¿Está seguro de eliminar este producto?</p>
                    <form action="servletProductos" method="post">
                        <input type="hidden" name="trackID" id="trackID">
                        <div class="field">
                            <div class="control">
                                <button class="button is-light" id="closeModalButton" type="button">Cancelar</button>
                                <button class="button is-success" name="accion" value="borrar">Eliminar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            const modal = document.getElementById('myModal');
            const openModalButtons = document.querySelectorAll('.openModalButton');
            const closeModalButton = document.getElementById('closeModalButton');
            const trackIDInput = document.getElementById('trackID');

            openModalButtons.forEach(button => {
                button.addEventListener('click', (event) => {
                    const trackID = event.target.getAttribute('data-trackid');
                    trackIDInput.value = trackID;
                    modal.classList.add('is-active');
                });
            });

            openModalButton.addEventListener('click', () => {
                modal.classList.add('is-active');
            });

            closeModalButton.addEventListener('click', () => {
                modal.classList.remove('is-active');
            });

            modal.addEventListener('click', (event) => {
                if (event.target === modal) {
                    modal.classList.remove('is-active');
                }
            });
        </script>
    </body>
</html>