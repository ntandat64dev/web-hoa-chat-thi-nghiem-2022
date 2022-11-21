<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý khách hàng | Quản trị Admin</title>

    <!-- ===== STYLESHEET ===== -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css" />

    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css" />
</head>

<body class="app sidebar-mini rtl">
<%--    Header--%>
<jsp:include page="../common/admin-header.jsp"/>

<!-- Sidebar Menu -->
<jsp:include page="../common/admin-sidebar-menu.jsp"/>

    <main class="app-content">
        <div class="app-title">
            <ul class="app-breadcrumb breadcrumb side">
                <li class="breadcrumb-item active"><a href="#"><b>Danh sách khách hàng</b></a></li>
            </ul>
            <div id="clock"></div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="tile">
                    <div class="tile-body">
                        <!-- Action Buttons -->
                        <div class="row element-button">
                            <div class="col-sm-2">
                                <a class="btn btn-delete btn-sm print-file" type="button" title="In"><i class="fas fa-print"></i> In dữ liệu</a>
                            </div>
                            <div class="col-sm-2">
                                <a class="btn btn-excel btn-sm" href="" title="In"><i class="fas fa-file-excel"></i> Xuất Excel</a>
                            </div>
                            <div class="col-sm-2">
                                <a class="btn btn-delete btn-sm pdf-file" type="button" title="In"><i class="fas fa-file-pdf"></i> Xuất PDF</a>
                            </div>
                        </div>
                        <!-- Search -->
                        <div class="search-bar d-flex justify-content-between my-3">
                            <div class="d-flex align-items-center">
                                <span class="status">Hiện <span class="quantity">10</span> danh mục</span>
                            </div>
                            <div class="search-wrap">
                                <label class="font-weight-bold m-0" for="search">Tìm kiếm: </label>
                                <input type="text" placeholder="" id="search">
                                <label for="by" class="font-weight-bold m-0 ml-3">Theo: </label>
                                <select id="by">
                                    <option>ID</option>
                                    <option>Tên</option>
                                </select>
                            </div>
                        </div>
                        <!-- Table -->
                        <table class="table table-hover table-bordered js-copytextarea" cellpadding="0" cellspacing="0" id="sampleTable">
                            <thead>
                                <tr>
                                    <th>ID khách hàng</th>
                                    <th>Họ và tên</th>
                                    <th width="20">Ảnh</th>
                                    <th width="300">Địa chỉ</th>
                                    <th>Ngày sinh</th>
                                    <th>Giới tính</th>
                                    <th>SĐT</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>CD12837</td>
                                    <td>Hồ Thị Thanh Ngân</td>
                                    <td><img class="img-card-person" src="https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt=""></td>
                                    <td>155-157 Trần Quốc Thảo, Quận 3, Hồ Chí Minh </td>
                                    <td>12/02/1999</td>
                                    <td>Nữ</td>
                                    <td>0926737168</td>
                                </tr>
                                <tr>
                                    <td>SX22837</td>
                                    <td>Trần Khả Ái</td>
                                    <td><img class="img-card-person" src="https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg?auto=compress&cs=tinysrgb&w=600" alt=""></td>
                                    <td>6 Nguyễn Lương Bằng, Tân Phú, Quận 7, Hồ Chí Minh</td>
                                    <td>22/12/1999</td>
                                    <td>Nữ</td>
                                    <td>0931342432</td>
                                </tr>
                                <tr>
                                    <td>LO28471</td>
                                    <td>Phạm Thu Cúc</td>
                                    <td><img class="img-card-person" src="https://images.pexels.com/photos/1239291/pexels-photo-1239291.jpeg?auto=compress&cs=tinysrgb&w=600" alt=""></td>
                                    <td>Số 3 Hòa Bình, Phường 3, Quận 11, Hồ Chí Minh </td>
                                    <td>02/06/1998</td>
                                    <td>Nữ</td>
                                    <td>0931491997</td>
                                </tr>
                                <tr>
                                    <td>SR28746</td>
                                    <td>Trần Anh Khoa</td>
                                    <td><img class="img-card-person" src="https://images.pexels.com/photos/11883419/pexels-photo-11883419.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="">
                                    </td>
                                    <td>19 Đường Nguyễn Hữu Thọ, Tân Hưng, Quận 7, Hồ Chí Minh </td>
                                    <td>18/02/1995</td>
                                    <td>Nam</td>
                                    <td>0916706633</td>
                                </tr>
                                <tr>
                                    <td>KJS4276</td>
                                    <td>Nguyễn Thành Nhân</td>
                                    <td><img class="img-card-person" src="https://images.pexels.com/photos/3990502/pexels-photo-3990502.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load" alt=""></td>
                                    <td>Số 13, Tân Thuận Đông, Quận 7, Hồ Chí Minh </td>
                                    <td>10/03/1996</td>
                                    <td>Nam</td>
                                    <td>0971038066</td>
                                </tr>
                                <tr>
                                    <td>BS76228</td>
                                    <td>Nguyễn Đặng Trọng Nhân</td>
                                    <td><img class="img-card-person" src="https://images.pexels.com/photos/1510601/pexels-photo-1510601.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load" alt=""></td>
                                    <td>59C Nguyễn Đình Chiểu, Quận 3, Hồ Chí Minh </td>
                                    <td>23/07/1996</td>
                                    <td>Nam</td>
                                    <td>0846881155</td>
                                </tr>
                                <tr>
                                    <td>YUI2136</td>
                                    <td>Nguyễn Thị Mai</td>
                                    <td><img class="img-card-person" src="https://images.pexels.com/photos/2112730/pexels-photo-2112730.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load" alt=""></td>
                                    <td>Đường Số 3, Tân Tạo A, Bình Tân, Hồ Chí Minh</td>
                                    <td>09/12/2000</td>
                                    <td>Nữ </td>
                                    <td>0836333037</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- ===== JAVASCRIPT ===== -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="vendor/bootstrap/js/popper.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/plugins/pace.min.js"></script>
    <script src="js/main.js"></script>
    <!-- ================================================================================================== -->
    <script>
        // In dữ liệu
        var myApp = new (function () {
            this.printTable = function () {
                var tab = document.getElementById("sampleTable");
                var win = window.open("", "", "height=700,width=700");
                win.document.write(tab.outerHTML);
                win.document.close();
                win.print();
            };
        })();

        // Sao chép dữ liệu
        // var copyTextareaBtn = document.querySelector('.js-textareacopybtn');

        // copyTextareaBtn.addEventListener('click', function (event) {
        //     var copyTextarea = document.querySelector('.js-copytextarea');
        //     copyTextarea.focus();
        //     copyTextarea.select();

        //     try {
        //         var successful = document.execCommand('copy');
        //         var msg = successful ? 'successful' : 'unsuccessful';
        //         console.log('Copying text command was ' + msg);
        //     } catch (err) {
        //         console.log('Oops, unable to copy');
        //     }
        // });
    </script>
</body>

</html>