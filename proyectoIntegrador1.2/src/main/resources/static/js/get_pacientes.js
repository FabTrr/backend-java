window.addEventListener('load', function() {
    fetch('/pacientes')
        .then(response => response.json())
        .then(data => {
            let tableBody = document.querySelector('#PacienteTableBody');
            data.forEach(paciente => {
                let row = document.createElement('tr');

                row.innerHTML = `
                    <td>${paciente.id}</td>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.apellido}</td>
                    <td>${paciente.cedula}</td>
                    <td>${paciente.fechaIngreso}</td>
                    <td>${paciente.domicilio.calle}</td>
                    <td>${paciente.email}</td>
                    <td><a href="update_paciente.html?id=${paciente.id}&nombre=${paciente.nombre}&apellido=${paciente.apellido}&cedula=${paciente.cedula}&fechaIngreso=${paciente.fechaIngreso}&calle=${paciente.domicilio.calle}&numero=${paciente.domicilio.numero}&localidad=${paciente.domicilio.localidad}&provincia=${paciente.domicilio.provincia}&email=${paciente.email}" class="btn btn-warning">Actualizar</a></td>
                    <td><button class="btn btn-danger" onclick="eliminarPaciente(${paciente.id})">Eliminar</button></td>
                `;
                tableBody.appendChild(row);
            });
        });
});

function eliminarPaciente(id) {
    if (confirm('¿Está seguro que desea eliminar este paciente?')) {
        fetch(`/pacientes/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Error al eliminar paciente');
            }
        });
    }
}
