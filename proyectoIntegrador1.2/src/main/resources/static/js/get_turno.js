window.addEventListener('load', function() {
    fetchAndDisplayTurnos();

    function fetchAndDisplayTurnos() {
        const url = '/turnos';
        const settings = {
            method: 'GET'
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('TurnoTableBody');
                tableBody.innerHTML = '';

                if (data.length === 0) {
                    const emptyRow = document.createElement('tr');
                    emptyRow.innerHTML = `
                        <td colspan="6" class="text-center">No hay turnos disponibles</td>
                    `;
                    tableBody.appendChild(emptyRow);
                } else {
                    data.forEach(turno => {
                        const turnoRow = document.createElement('tr');
                        turnoRow.innerHTML = `
                            <td>${turno.id}</td>
                            <td>${turno.pacienteId}</td>
                            <td>${turno.odontologoId}</td>
                            <td>${turno.fecha} ${turno.hora}</td>
                            <td><button type="button" onclick="showUpdateForm(${turno.id})" class="btn btn-warning">Actualizar</button></td>
                            <td><button type="button" onclick="eliminarTurno(${turno.id})" class="btn btn-danger">Eliminar</button></td>
                        `;
                        tableBody.appendChild(turnoRow);
                    });
                }
            })
            .catch(error => console.error('Error listando turnos:', error));
    }

    window.eliminarTurno = function(id) {
        if (confirm(`¿Estás seguro de eliminar el turno ${id}?`)) {
            fetch(`/turnos/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    location.reload(); // Recarga la página para actualizar la lista de turnos
                } else {
                    alert('Error al eliminar turno');
                }
            })
            .catch(error => console.error(`Error eliminando turno ${id}:`, error));
        }
    };

    window.showUpdateForm = function(id) {
        window.location.href = `/update_turno.html?id=${id}`;
    };

    const pathname = window.location.pathname;
    if (pathname.includes("/get_turnos.html")) {
        document.querySelector(".nav .nav-item a[href='./get_turnos.html']").classList.add("active");
    }
});
