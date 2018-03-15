/**
 * Created by VictorJr on 26/03/2017.
 */

    $(function() {
        $('.js-decimal').maskMoney({
            thousands: '.',
            decimal: ',',
            affixesStay: false,
            selectAllOnFocus: true
        });

        var inteiro = $('.inteiro');
        inteiro.maskMoney({precision:0, thousands:'.', decimal:','});
    });


    // calculation to provide profit percent
    $('#pcompra').add('#pvenda').keyup(function() {
        var pcompra = parseFloat($('#pcompra').val());
        var pvenda = parseFloat($('#pvenda').val());
        var percen = (parseFloat(((pvenda-pcompra)/pcompra)*100)).toFixed(2);
        var percenString = (parseFloat(percen)).toString();
        //percenString = percenString.replace(".",",");
        $('#percen').val(percenString);
    });

    // updating selling price text field through JS
    $('#products').change(function () {
       $('#sellingPrice').val($('#products').val()).toString();
    });
    
    // updating total selling price according to product value and quantity
    $('#qtdSelling').change(function () {
        var qtdSelling = parseInt($('#qtdSelling').val());
        var sellingPrice = parseFloat($('#sellingPrice').val());
        $('#sellingTotal').val(qtdSelling*sellingPrice);
    });



    $(document).ready(function () {
        var counter = 0;

        $("#addrow").on("click", function () {
            var newRow = $("<tr>");
            var cols = "";

            cols += '<td><input type="text" class="form-control" name="name' + counter + '"/></td>';
            cols += '<td><input type="text" class="form-control" name="mail' + counter + '"/></td>';
            cols += '<td><input type="text" class="form-control" name="phone' + counter + '"/></td>';

            cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
            newRow.append(cols);
            $("table.order-list").append(newRow);
            counter++;
        });



        $("table.order-list").on("click", ".ibtnDel", function (event) {
            $(this).closest("tr").remove();
            counter -= 1
        });


    });










