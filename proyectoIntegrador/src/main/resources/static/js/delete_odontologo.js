function deleteBy(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
    .then(response => {
        if (response.ok) {
            // Remove the row from the table
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
            alert("Odontólogo eliminado exitosamente.");
        } else {
            alert("Error al eliminar el odontólogo.");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Error al eliminar el odontólogo.");
    });
}
