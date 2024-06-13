window.addEventListener('load', function() {
    fetch('/odontologos')
        .then(response => response.json())
        .then(data => {
            let tableBody = document.querySelector('#OdontologoTableBody');
            data.forEach(odontologo => {
                let row = document.createElement('tr');

                row.innerHTML = `
                    <td>${odontologo.id}</td>
                    <td>${odontologo.numeroMatricula}</td>
                    <td>${odontologo.nombre.toUpperCase()}</td>
                    <td>${odontologo.apellido.toUpperCase()}</td>
                    <td><a href="update_odontologo.html?id=${odontologo.id}&nombre=${odontologo.nombre}&apellido=${odontologo.apellido}&matricula=${odontologo.numeroMatricula}" class="btn btn-warning">Actualizar</a></td>
                    <td><button class="btn btn-danger" onclick="eliminarOdontologo(${odontologo.id})">Eliminar</button></td>
                `;
                tableBody.appendChild(row);
            });
        });
});

function eliminarOdontologo(id) {
    if (confirm('¿Esta seguro que desea eliminar el odontologo?')) {
        fetch(`/odontologos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Error al eliminar odontologo');
            }
        });
    }
}
