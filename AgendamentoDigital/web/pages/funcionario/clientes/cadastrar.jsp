<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendamento Digital | Cadastrar Cliente</title>
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
            <jsp:include page="../sidebar-menu.jsp"/>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1 class="m-0 text-dark">Cadastrar Cliente</h1>
                            </div><!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item active">Cliente</li>
                                </ol>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <section class="content">
                    <div class="container-fluid">
                        <!-- Main row -->
                        <div class="row align-items-center justify-content-center">
                            <!-- Left col -->
                            <section class="col-lg-7 connectedSortable">
                                <div class="row align-items-center justify-content-center">
                                    <div class="register-box">

                                        <div class="card">
                                            <div class="card-body register-card-body">
                                                <p class="login-box-msg">Cadastrar um novo cliente</p>

                                                <form id="cadastrarUsuario" autocomplete="off" action="${site}/Funcionario/Cliente/Cadastrar"  method="post">
                                                    <div class="input-group mb-3">
                                                        <input id="nome" name="nome" type="text" class="form-control" placeholder="Nome completo">
                                                        <div class="input-group-append">
                                                            <div class="input-group-text">
                                                                <span class="fas fa-user"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <input id="dataNascimento" name="dataNascimento" type="date" class="form-control" placeholder="Data Nascimento">
                                                        <div class="input-group-append">
                                                            <div class="input-group-text">
                                                                <span class="fas fa-calendar"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- phone mask -->
                                                    <div class="input-group mb-3">
                                                        <input id="celular" name="celular" type="text" class="form-control mask" placeholder="Celular" data-mask='telefone'>
                                                        <div class="input-group-prepend">
                                                            <div class="input-group-text">
                                                                <span class="fas fa-phone"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <input id="email" name="email" type="text" class="form-control mask" placeholder="Email" data-mask='email'>
                                                        <div class="input-group-append">
                                                            <div class="input-group-text">
                                                                <span class="fas fa-envelope"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <input id="senha" name="senha" type="password" class="form-control" placeholder="Digite uma senha">
                                                        <div class="input-group-append">
                                                            <div class="input-group-text">
                                                                <span class="fas fa-lock"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="input-group mb-3">
                                                        <input id="chksenha" name="chksenha" type="password" class="form-control" placeholder="Confirme sua senha">
                                                        <div class="input-group-append">
                                                            <div class="input-group-text">
                                                                <span class="fas fa-lock"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-4">
                                                            <button type="submit" class="btn btn-block btn-success">Cadastrar</button>
                                                        </div>
                                                        <!-- /.col -->
                                                    </div>
                                                </form>
                                            </div>
                                            <!-- /.form-box -->
                                        </div><!-- /.card -->
                                    </div>
                                    <!-- /.register-box -->
                                </div>
                            </section>
                        </div>
                        <!-- /.row (main row) -->
                    </div><!-- /.container-fluid -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <!-- Footer -->
            <jsp:include page="../footer.jsp"/>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->
        </div>
        <!-- ./wrapper -->

        <script src="${site}/dist/js/pages/includeHTMLNav.js" type="text/javascript"></script>
        <script>
            includeHTMLNav("usuarios", "cadastrarUsuario");
        </script>
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
        <!-- Mask js -->
        <script src="${site}/dist/js/myMask.js" type="text/javascript"></script>
        <!-- SweetAlert2 -->
        <script src="${site}/plugins/sweetalert2/sweetalert2.min.js"></script>
        <script src="${site}/plugins/sweetalert2/sweetalert2.js"></script>
        <script src="${site}/pages/funcionario/clientes/cadastrar.js" type="text/javascript"></script>
        <script>
            $(function () {
                //Initialize Select2 Elements
                $('.select2').select2()

                //Initialize Select2 Elements
                $('.select2bs4').select2({
                    theme: 'bootstrap4'
                })

                $('[data-mask]').inputmask()

            });
            sweet("${funcaoMsg}", "${funcaoStatus}", 2000);
        </script>

        <!-- PAGE PLUGINS -->
        <!-- jQuery Mapael -->
        <script src="${site}/plugins/jquery-mousewheel/jquery.mousewheel.js"></script>
        <script src="${site}/plugins/raphael/raphael.min.js"></script>
        <script src="${site}/plugins/jquery-mapael/jquery.mapael.min.js"></script>
        <script src="${site}/plugins/jquery-mapael/maps/usa_states.min.js"></script>
    </body>
</html>