$(document).ready(function () {

    let table = $('#table-alumnos').DataTable({
        serverSide: true,
        processing: true,
        stateSave: true,
        responsive: true,
        language: {
            url: urlApp + 'plugins/datatables/es-MX.json'
        },
        search: {
            smart: true,
            regex: false,
            caseInsensitive: true
        },
        pageLength: 10,
        lengthMenu: [10, 25, 50, 100, 200],
        order: [[1, 'asc']],
        "ajax": {
            "url": urlApp + "alumnos/getAll",
            "type": "GET",
            "data": function (d) {
                d.page = d.start / d.length;
                d.size = d.length;
                //return JSON.stringify(d)
            }
        },
        "columns": [
            {
                "data": "idalumno", visible: false,
                responsivePriority: 1
            },
            {
                "data": "paterno",
                responsivePriority: 2
            }, {
                "data": "materno",
                responsivePriority: 2
            }, {
                "data": "nombre",
                responsivePriority: 2
            },
            {
                "data": "curp",
                responsivePriority: 2
            },
            {
                "data": "fechanacimiento",
                responsivePriority: 2
            },
            {
                "data": "idcondicion",
                responsivePriority: 2,
                render: function (data) {
                    return data.clave;
                }
            },

            {
                "data": "rgrado",
                responsivePriority: 3,
                render: function (data) {
                    return data.grupo;
                }
            },
           /* {
                "data": "igrado",
                responsivePriority: 3,
                render: function (data) {
                    if (data.grupo === null)
                        return "";
                    return data.grupo;

                }
            },*/
            {
                data: null,
                render: function (data, type, row) {
                    return row.paterno + " " + row.materno + " " + row.nombre;
                }
            },
            {
                "data": null,
                responsivePriority: 1,
                class: 'text-nowrap',
                render: function (data,type,row) {
                    let msj = '';

                    msj += `<button type="button" class="btn btn-info btn-sm ms-2 btn-editar d-none" data-id="${row.idalumno}" 
								data-bs-toggle="tooltip" data-bs-placement="top" title="Editar alumno">
										<span class="fa fa-pencil-square-o"></span>
							</button>`;
                    msj += `<button type="button" class="btn btn-danger btn-sm ms-2 btn-eliminar" data-id="${row.idalumno}" 
								data-bs-toggle="tooltip" data-bs-placement="top" title="Eliminar usuario">
										<span class="fa fa-trash"></span>
								</button>`;
                    return msj;
                },
                "orderable": false, // Deshabilitar ordenamiento para esta columna
                "searchable": false,
            }

        ]
    });

    $('#nacimiento').datepicker();
});

$(document).on("click",".btn-editar",function (e) {
   $("#modaleditAlumno").modal("show");
})

