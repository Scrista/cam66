$(document).ready(function(){

    let table = $('#table-Material').DataTable({
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
            "url": URL_GET_MATERIAL,
            "type": "GET",
            "data": function (d) {
                d.page = d.start / d.length;
                d.size = d.length;
                //return JSON.stringify(d)
            }
        },
        "columns": [
            {
                "data": "id",
                responsivePriority: 1
            },
            {
                "data": "articulo",
                responsivePriority: 2
            }, {
                "data": "piezas",
                responsivePriority: 2
            }, {
                "data": "cantidadRestante",
                responsivePriority: 2
            }, {
                "data": "estado",
                responsivePriority: 2
            }
        ]
    });
})


$(document).on("click","#btnNuevoResguardo",function (e) {
    $("#modalNuevoResguardo").modal("show");
})



$('#modalNuevoResguardo').on('shown.bs.modal', function () {


    const $hub = $("#articuloResguardo");

    // Evitar doble inicialización
    if ($hub.data("kendoMultiSelect")) {
        return;
    }

    $hub.kendoMultiSelect({
        dataTextField: "articulo",
        dataValueField: "id",
        dataSource: {
            transport: {
                read: URL_GET_ARTICULO,
                dataType: "json",
                type: "GET"
            },
        },
        placeholder: "Selecciona Material",
        autoClose: false
    });

});

$(document).on("click","#btnGuardarResguardo",function (){
alert(1)
})