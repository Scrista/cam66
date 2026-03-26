let selectedIds = new Set();
$(document).ready(function(){


    let table = $('#table-impresiones').DataTable({
        serverSide: true,
        processing: true,
        stateSave: true,
        responsive: true,
        language: {
            url: urlApp + 'plugins/datatables/es-MX.json'
        },
        pageLength: 10,
        order: [[1, 'asc']],
        initComplete:function (settings, json) {
            $("#porCobrar").text("$"+json.total);
        },

        ajax: {
            url: URL_GET_IMPRESIONES,
            type: "GET",
            data: function (d) {
                d.page = d.start / d.length;
                d.size = d.length;

            }
        },

        columns: [

            {
                data: null,
                orderable: false,
                searchable: false,
                className: 'text-center',
                render: function (data, type, row) {
                    let checked = selectedIds.has(row.id) ? 'checked' : '';
                    return `<input type="checkbox" class="row-check" value="${row.id}" ${checked}>`;
                }
            },

            { data: "id", visible: false },
            {
                data: "idpersonal",
                render: data => data.nombre
            },
            { data: "fecha" },
            { data: "cantidad" },
            { data: "cobrar" },
            {
                data: "cobrado",
                render: data => data ? "NO" : "SI"
            },
            {
                data: null,
                class: 'text-nowrap',
                render: function (data, type, row) {

                    if(!row.cobrado)
                    return `
                    <button type="button" 
                        class="btn btn-info btn-sm ms-2 btn-editar d-none" 
                        data-id="${row.id}">
                        <span class="fa fa-money"></span>
                    </button>`;

                },
                orderable: false,
                searchable: false
            }
        ]
    });

    table.on('draw', function () {
        $('.row-check').each(function () {
            let id = $(this).val();
            if (selectedIds.has(id)) {
                $(this).prop('checked', true);
            }
        });
    });
})

$('#check-all').on('change', function () {
    let checked = this.checked;

    $('.row-check').each(function () {
        $(this).prop('checked', checked).trigger('change');
    });
});

$('#table-impresiones').on('change', '.row-check', function () {
    let id = $(this).val();

    if (this.checked) {
        selectedIds.add(id);
    } else {
        selectedIds.delete(id);
    }
});


$(document).on("click","#btnNuevaImpresion",function (e) {
    $("#modalNuevaImpresion").modal("show");
})



$('#modalNuevaImpresion').on('shown.bs.modal', function () {


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

$(document).on("click","#btnGuardarbitacora",function (){

    $.ajax({
        url: URL_POST_BITACORAIMPRESION,
        data: {
            cantidad:$("#cantidaImpresion").val(),
            costo:$("#costoImpresion").val(),
            idpersonal:$("#personalSelect").val()
        },

        dataType: 'json',
        sync: true,
        success: function (data) {


        },
        error: function (data, status) {


            // app.hideLoading();

            if (data.responseText.includes("redirect")) {
                app.hideLoading();
                showCaduca();
            }
        },

    })

});


