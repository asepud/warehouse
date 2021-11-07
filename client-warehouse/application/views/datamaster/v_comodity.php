<!-- Row -->
<div class="row mb-3">
    <!-- Datatables -->
    <div class="col-lg-12">
        <div class="card mb-4">
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary"></h6>
                <!-- <button type="button" class="btn btn-outline-primary mb-1" data-toggle="modal" data-target="#comodityModal"><i class="fas fa-plus-square"></i> Add</button> -->
                <button type="button" class="btn btn-outline-primary mb-1" id="AddComodity"><i class="fas fa-plus-square"></i> Add</button>
            </div>
            <div class="table-responsive p-3">
                <table class="table align-items-center table-flush" id="comodity">
                    <thead class="thead-light">
                        <tr>
                            <th>No</th>
                            <th>Comodity Name</th>
                            <th>Unit Name</th>
                            <th>Measurement</th>
                            <th style="display:none;">Measurement</th>
                            <th>Category</th>
                            <th style="display:none;">description </th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                        $i = 1;
                        foreach ($comodity as $key => $comodity) {

                        ?>
                            <tr>
                                <td><?= $i ?></td>
                                <td id="getName"><?= $comodity->comodityPK->name ?></td>
                                <td id="getUnit"><?= $comodity->unit ?></td>
                                <td style="display:none;" id="getMeasure"><?= $comodity->measurement->measurementPK->name ?></td>
                                <td id="getMeasureNickname"><?= $comodity->measurement->nickName ?></td>
                                <td id="getCategory"><?= $comodity->category->description?></td>
                                <td id="getCategory" style="display:none;"><?= $comodity->category->categoryPK->name?></td>
                                <td>
                                    <button class="btn btn-outline-warning mb-3" id="editComodity"><i class="far fa-edit"></i> Edit</button>
                                    <button class="btn btn-outline-danger mb-3" id="deleteComodity"><i class="fas fa-trash-alt"></i> Hapus</button>
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

<div class="modal fade" id="comodityModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabelLogout" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabelLogout">Add Comodity</h5>
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
                                        <label for="exampleInputEmail1">Comodity Name</label>
                                        <input type="text" class="form-control" id="comodityName" aria-describedby="comodityName" placeholder="Enter Name">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Unit Name</label>
                                        <input type="text" class="form-control" id="comodityUnit" aria-describedby="comodityUnit" placeholder="Enter Unit">
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Measurement</label>
                                        <select class="comodityMeasurement form-control" id="comodityMeasurement" name="comodityMeasurement"></select>
                                    </div>

                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Category</label>
                                        <select class="comodityCategory form-control" id="comodityCategory" name="comodityCategory"></select>
                                        <!-- <textarea class="form-control" id="comodityCategory" rows="3"></textarea> -->
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

    $('.comodityCategory').select2({
        placeholder: 'Select an item',
        ajax: {
            url: '<?= base_url('comodity/get_category') ?>',
            dataType: 'json',
            delay: 250,
            data: function (data) {
                console.log(data)
                return {
                    searchTerm: data.term // search term
                };
            },
            processResults: function (response) {
                return {
                    results:response
                };
            },
            cache: true
        }
    });

    $('.comodityMeasurement').select2({
        placeholder: 'Select an item',
        ajax: {
            url: '<?= base_url('comodity/get_measurement') ?>',
            dataType: 'json',
            delay: 250,
            data: function (data) {
               
                return {
                    searchTerm: data.term // search term
                };
            },
            processResults: function (response) {
                console.log(response)
                return {
                    results:response
                };
            },
            cache: true
        }
    });

    $(document).ready(function() {

        $("#AddComodity").click(function() {

            document.getElementById("comodityName").value = '';
            document.getElementById("comodityUnit").value = '';
            // document.getElementById("comodityMeasurement").value = '';
            // document.getElementById("comodityCategory").value = '';
            document.getElementById("edit").value = '';

            $("#comodityModal").modal("show");
        });

        let table = $('#comodity').DataTable();

        $("#submit").click(function() {

            var name = $("#comodityName").val();
            var unit = $("#comodityUnit").val();
            var measure = $("#comodityMeasurement").val();
            var category = $("#comodityCategory").val();

         
            var edit = $("#edit").val();
            if (edit == 'Edit') {
                var uri = "<?php echo base_url() ?>comodity/edit";
            } else {
                var uri = "<?php echo base_url() ?>comodity/add";
            }

            $.ajax({

                url: uri,
                type: "POST",
                data: {
                    "name": name,
                    "unit": unit,
                    "measure": measure,
                    "category": category
                },
                success: function(response) {
                   var status = JSON.parse(response);
                    if (status['status']) {

                        Swal.fire({
                                type: 'success',
                                title: 'Data Berhasil!',
                                text: 'Anda akan di arahkan dalam 3 Detik',
                                timer: 3000,
                                showCancelButton: false,
                                showConfirmButton: false
                            })
                            .then(function() {
                                window.location.href = "<?php echo base_url() ?>comodity";
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
            return false
        });



        $('#comodity tbody').on('click', '#editComodity', function() {
            var current_row = $(this).parents('tr');
            if (current_row.hasClass('child')) {
                current_row = current_row.prev();
            }
            var data = table.row(current_row).data();
            console.log(data)

            document.getElementById("comodityName").value = data[1];
            document.getElementById("comodityUnit").value = data[2];
            // document.getElementById("comodityMeasurement").value = data[3];
            // $("#comodityMeasurement").append(data[3]).trigger('change');
          
            var newOption = new Option(data[4], data[3], false, false);
            $('#comodityMeasurement').append(newOption).trigger('change');

            var newOption = new Option(data[6], data[5], false, false);
            $('#comodityCategory').append(newOption).trigger('change');
            // $("#comodityMeasurement").select2().val([data[3]]).trigger("change");
            document.getElementById("comodityCategory").value = data[4];

            document.getElementById("edit").value = 'Edit';
            $("#comodityModal").modal("show");
        });

        $('#comodity tbody').on('click', '#deleteComodity', function() {
            var current_row = $(this).parents('tr');
            if (current_row.hasClass('child')) {
                current_row = current_row.prev();
            }
            var data = table.row(current_row).data();

            // document.getElementById("comodityName").value = data[1];
            // document.getElementById("comodityCategory").value = data[2];

            $.ajax({

                url: '<?php echo base_url() ?>comodity/hapus',
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
                                window.location.href = "<?php echo base_url() ?>comodity";
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