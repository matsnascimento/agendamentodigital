<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendamento Digital | ${pgAba}</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="${site}/plugins/fontawesome-free/css/all.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Tempusdominus Bbootstrap 4 -->
        <link rel="stylesheet" href="${site}/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="${site}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
        <!-- JQVMap -->
        <link rel="stylesheet" href="${site}/plugins/jqvmap/jqvmap.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${site}/dist/css/adminlte.min.css">
        <!-- overlayScrollbars -->
        <link rel="stylesheet" href="${site}/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
        <!-- Daterange picker -->
        <link rel="stylesheet" href="${site}/plugins/daterangepicker/daterangepicker.css">
        <!-- summernote -->
        <link rel="stylesheet" href="${site}/plugins/summernote/summernote-bs4.css">
        <!-- SweetAlert2 -->
        <link rel="stylesheet" href="${site}/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
        <!-- Google Font: Source Sans Pro -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    </head>
    <body class="hold-transition sidebar-mini layout-boxed sidebar-collapse">
        <div class="wrapper">

            <!-- Navbar & Menu-->
            <jsp:include page="/pages/${pgperfil}/sidebar-menu.jsp"/>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>${pgTitulo}</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active">${pgTitulo}</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->
                </section>
                <form id="filtrarPeriodo" action="${site}/Relatorios/Servicos/${command}/Periodo" method="post">
                    <section class="content">

                        <div class="container-fluid mb-2 col-12">      

                            <div class="row">
                                <!-- Left Column -->
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <select name="mes" id="mes" class="form-control select select-danger col-6" data-dropdown-css-class="select2-danger">
                                            <option selected disabled value="null">-- Selecione o mes --</option>
                                            <option value="1">Janeiro</option>
                                            <option value="2">Fevereiro</option>
                                            <option value="3">Março</option>
                                            <option value="4">Abril</option>
                                            <option value="5">Maio</option>
                                            <option value="6">Junho</option>
                                            <option value="7">Julho</option>
                                            <option value="8">Agosto</option>
                                            <option value="9">Setembro</option>
                                            <option value="10">Outubro</option>
                                            <option value="11">Novembro</option>
                                            <option value="12">Dezembro</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <select name="ano" id="ano" class="form-control select select-danger col-6" data-dropdown-css-class="select2-danger">
                                            <option selected disabled>-- Selecione o ano --</option>
                                            <option value="2020">2020</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <select name="status" id="status" class="form-control select select-danger col-6" data-dropdown-css-class="select2 - danger" placeholder="Selecione o estado">
                                            <option selected disabled value="null">-- Selecione a situação --</option>
                                            <option value="FINALIZADO">Finalizado</option>
                                            <option value="AGUARDANDOATENDIMENTO">Aguardando atendimento</option>
                                            <option value="CANCELADO">Cancelado</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="align-center">
                        <div class="col-2 mb-2">
                            <button id="btnFiltar" type="submit" class="btn btn-primary btn-block">Filtrar</button>
                        </div>
                    </div>

                </form>


                <!-- Main content -->
                <section class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h3 class="card-title">${pgRelatorio}</h3>
                                        <div class="card-tools">
                                            <div class="input-group input-group-sm" style="width: 150px;">

                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.card-header -->
                                    <div id="target" class="card-body p-0"></div>
                                    <!-- /.card-body -->
                                </div>
                                <!-- /.card -->
                                <!-- /.col -->
                            </div>
                        </div>
                        <!-- /.row -->
                    </div><!-- /.container-fluid -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <!-- Footer -->
            <jsp:include page="../../${pgperfil}/footer.jsp"/>

        </div>
        <!-- ./wrapper -->
    </body>

    <script src="${site}/dist/js/pages/includeHTMLNav.js" type="text/javascript"></script>
    <!-- jQuery -->
    <script src="${site}/plugins/jquery/jquery.min.js"></script>

    <!-- jQuery UI 1.11.4 -->
    <script src="${site}/plugins/jquery-ui/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
        $.widget.bridge('uibutton', $.ui.button)
    </script>
    <!-- Bootstrap 4 -->
    <script src="${site}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- ChartJS -->
    <script src="${site}/plugins/chart.js/Chart.min.js"></script>
    <!-- Sparkline -->
    <script src="${site}/plugins/sparklines/sparkline.js"></script>
    <!-- JQVMap -->
    <script src="${site}/plugins/jqvmap/jquery.vmap.min.js"></script>
    <script src="${site}/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
    <!-- jQuery Knob Chart -->
    <script src="${site}/plugins/jquery-knob/jquery.knob.min.js"></script>
    <!-- daterangepicker -->
    <script src="${site}/plugins/moment/moment.min.js"></script>
    <script src="${site}/plugins/daterangepicker/daterangepicker.js"></script>
    <!-- Tempusdominus Bootstrap 4 -->
    <script src="${site}/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
    <!-- Summernote -->
    <script src="${site}/plugins/summernote/summernote-bs4.min.js"></script>
    <!-- overlayScrollbars -->
    <script src="${site}/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
    <!-- AdminLTE App -->
    <script src="${site}/dist/js/adminlte.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="${site}/dist/js/pages/dashboard.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="${site}/dist/js/demo.js"></script>
    <!-- InputMask -->
    <script src="${site}/plugins/moment/moment.min.js"></script>
    <script src="${site}/plugins/inputmask/min/jquery.inputmask.bundle.min.js"></script>
    <!-- Select2 -->
    <script src="${site}/plugins/select2/js/select2.full.min.js"></script>
    <!-- SweetAlert2 -->
    <script src="${site}/plugins/sweetalert2/sweetalert2.min.js"></script>
    <script src="${site}/plugins/sweetalert2/sweetalert2.js"></script>
    <!-- Toastr -->
    <script src="${site}/plugins/toastr/toastr.min.js"></script>

    <script>
        $(function () {
            //Initialize Select2 Elements
            $('.select2').select2()

            //Initialize Select2 Elements
            $('.select2bs4').select2({
                theme: 'bootstrap4'
            })

            $('[data-mask]').inputmask()

        })
    </script>

    <script charset="ISO-8529" src="${site}/pages/funcionario/relatorios/${pgjs}.js" type="text/javascript"></script>

    <script>
        sweet("${funcaoMsg}", "${funcaoStatus}", 4000);
        var result = ${msg};
        includeHTMLNav("maisAgendado", "maisAgendado");
        lerJson(result);

    </script>

    <script src="${site}/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
    <script src="${site}/plugins/raphael/raphael.min.js"></script>
    <script src="${site}/plugins/jquery-mapael/jquery.mapael.min.js"></script>
    <script src="${site}/plugins/jquery-mapael/maps/usa_states.min.js"></script>

    <!-- PAGE PLUGINS -->
    <!-- jQuery Mapael -->
    <script src="${site}/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
    <script src="${site}/plugins/raphael/raphael.min.js"></script>
    <script src="${site}/plugins/jquery-mapael/jquery.mapael.min.js"></script>
    <script src="${site}/plugins/jquery-mapael/maps/usa_states.min.js"></script>
</html>
