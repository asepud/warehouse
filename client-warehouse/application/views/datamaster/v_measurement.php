<!-- Row -->
<div class="row mb-3">
    <!-- Datatables -->
    <div class="col-lg-12">
        <div class="card mb-4">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary"></h6>
                <!-- <button type="button" class="btn btn-outline-primary mb-1" data-toggle="modal" data-target="#measurementModal"><i class="fas fa-plus-square"></i> Add</button> -->
                <button type="button" class="btn btn-outline-primary mb-1" id="AddMeasurement"><i class="fas fa-plus-square"></i> Add</button>
            </div>
            <div class="table-responsive p-3">
                <table class="table align-items-center table-flush" id="measurement">
                    <thead class="thead-light">
                        <tr>
                            <th>No</th>
                            <th>Measurement Name</th>
                            <th>Nick Name</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                        $i = 1;
                        foreach ($measurement as $key => $measurement) {

                        ?>
                            <tr>
                                <td><?= $i ?></td>
                                <td id="getName"><?= $measurement->measurementPK->name ?></td>
                                <td id="getName"><?= $measurement->nickName ?></td>
                                <td id="getDesc"><?= $measurement->description ?></td>
                                <td>
                                    <button class="btn btn-outline-warning mb-3" id="editMeasurement"><i class="far fa-edit"></i> Edit</button>
                                    <button class="btn btn-outline-danger mb-3" id="deleteMeasurement"><i class="fas fa-trash-alt"></i> Hapus</button>
                                </td>
                            </tr>
                        <?php
                            $i++;
                        }
                        ?>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="measurementModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelLogout" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabelLogout">Add Measurement</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card mb-4">
                            <div class="card-body">
                                <form method="POST">
                                    <input type="hidden" class="form-control" id="edit">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Measurement Name</label>
                                        <input type="text" class="form-control" id="measurementName" aria-describedby="measurementName" placeholder="Enter Name">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Nick Name</label>
                                        <input type="text" class="form-control" id="measurementNick" aria-describedby="measurementNick" placeholder="Enter Nick">
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Measurement Description</label>
                                        <textarea class="form-control" id="measurementDesc" rows="3"></textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel</button>
                                        <button id="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Row-->
<script>
    $(document).ready(function() {

        $("#AddMeasurement").click(function() {

            document.getElementById("measurementName").value = '';
            document.getElementById("measurementNick").value = '';
            document.getElementById("measurementDesc").value = '';
            document.getElementById("edit").value = 'Edit';

            $("#measurementModal").modal("show");
        });

        let table = $('#measurement').DataTable();

        $("#submit").click(function() {

            var name = $("#measurementName").val();
            var nick = $("#measurementNick").val();
            var desc = $("#measurementDesc").val();
            var edit = $("#edit").val();
            if (edit == 'Edit') {
                var uri = "<?php echo base_url() ?>measurement/add";
            } else {
                var uri = "<?php echo base_url() ?>measurement/edit";
            }

            $.ajax({

                url: uri,
                type: "POST",
                data: {
                    "name": name,
                    "nick": nick,
                    "desc": desc
                },
                success: function(response) {
                    console.log(response)
                    if (response) {

                        Swal.fire({
                                type: 'success',
                                title: 'Data Berhasil!',
                                text: 'Anda akan di arahkan dalam 3 Detik',
                                timer: 3000,
                                showCancelButton: false,
                                showConfirmButton: false
                            })
                            .then(function() {
                                window.location.href = "<?php echo base_url() ?>measurement";
                            });

                    } else {

                        Swal.fire({
                            type: 'error',
                            title: 'Data Gagal!',
                            text: 'silahkan coba lagi!'
                        });
                    }

                },

                error: function(response) {

                    Swal.fire({
                        type: 'error',
                        title: 'Opps!',
                        text: 'server error!'
                    });

                }

            });
        });



        $('#measurement tbody').on('click', '#editMeasurement', function() {
            var current_row = $(this).parents('tr');
            if (current_row.hasClass('child')) {
                current_row = current_row.prev();
            }
            var data = table.row(current_row).data();
            console.log(data)

            document.getElementById("measurementName").value = data[1];
            document.getElementById("measurementNick").value = data[2];
            document.getElementById("measurementDesc").value = data[3];

            $("#measurementModal").modal("show");
        });

        $('#measurement tbody').on('click', '#deleteMeasurement', function() {
            var current_row = $(this).parents('tr');
            if (current_row.hasClass('child')) {
                current_row = current_row.prev();
            }
            var data = table.row(current_row).data();

            // document.getElementById("measurementName").value = data[1];
            // document.getElementById("measurementDesc").value = data[2];

            $.ajax({

                url: '<?php echo base_url() ?>measurement/hapus',
                type: "POST",
                data: {
                    "name": data[1],
                },
                success: function(response) {
                    console.log(response)
                    if (response) {

                        Swal.fire({
                                type: 'success',
                                title: 'Data Berhasil!',
                                text: 'Anda akan di arahkan dalam 3 Detik',
                                timer: 3000,
                                showCancelButton: false,
                                showConfirmButton: false
                            })
                            .then(function() {
                                window.location.href = "<?php echo base_url() ?>measurement";
                            });

                    } else {

                        Swal.fire({
                            type: 'error',
                            title: 'Data Gagal!',
                            text: 'silahkan coba lagi!'
                        });
                    }

                },

                error: function(response) {

                    Swal.fire({
                        type: 'error',
                        title: 'Opps!',
                        text: 'server error!'
                    });

                }

            });

        });
    });
</script>