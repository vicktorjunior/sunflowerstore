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
    $("#bprice, #sprice").keyup(function() {
        var bprice = parseFloat($('#bprice').val());
        var sprice = parseFloat($('#sprice').val());
        var percen = (parseFloat(((sprice-bprice)/bprice)*100)).toFixed(2);
        var percenString = (parseFloat(percen)).toString();
        //percenString = percenString.replace(".",",");
        $('#percen').val(percenString);
    });

    // updating selling price text field through JS
    /*$('#products').change(function () {
       $('#sellingPrice').val($('#products').val()).toString();
    });*/
    
    // updating total selling price according to product value and quantity
    /*$('#qtdSelling').change(function () {
        var qtdSelling = parseInt($('#qtdSelling').val());
        var sellingPrice = parseFloat($('#sellingPrice').val());
        $('#sellingTotal').val(qtdSelling*sellingPrice);
    });
*/

    $(document).ready(function () {
        $('#discount').val(0);
        $('#sellingTotal').val(0);
    });


   /* $('#addrow').click(function () {
        var qtdSelling = parseInt($('#qtdSelling').val());
        var sellingPrice = parseFloat($('#pVenda').val());
        var productName = $('#selectProd :selected').text();
        var discount = ((parseFloat($('#desconto').val())*sellingPrice)/100)*qtdSelling;
        var sellingTotal = (qtdSelling*sellingPrice)-discount;



        //console.log(discount);
        //console.log(sellingTotal);


        var chtml = "<tr>\n" +
            "                        <td> " + qtdSelling + "</td>\n" +
            "                        <td> " + productName + "</td>\n" +
            "                        <td>" + sellingPrice.toFixed(2) +" </td>\n" +
            "                        <td>" + discount.toFixed(2) +" </td>\n" +
            "                        <td class=\"sellingTotal\">" + sellingTotal.toFixed(2) +" </td>\n" +
            "                        <td><div class=\"col-sm-4\"><input type=\"button\" id=\"delete\" class=\"form-control btn btn-danger\" value=\"Delete\"/></div></td>\n" +
            "                    </tr>";

        $('#productsToSell').find('> tbody').append(chtml);


        $("#delete").click(function() {
            $(this).parents("tr").remove();
            $('#sellingTotal').val(0);
        });

        $('#qtdSelling').val(1);
        $('#selectProd').prop('selectedIndex',0);
        $('#desconto').val(0);
        $('#pVenda').val(0);
        $('#totProd').val(0)
        
    });*/
    
    



    $("#sumTotal").click(function () {
        var totals=0;

        var $dataRows=$("#productsToSell").find("tr:has('.sellingTotal')");

        $dataRows.each(function() {
            $(this).find('.sellingTotal').each(function(){
                totals+=parseFloat($(this).html());
            });
        });

        parseFloat($('#sellingTotal').val(totals));
    });

$(document).ready(function() {
    $("#discount, #qtdSelling, #selectProd").change(function(e) {
        var product = $('#selectProd').val();
        $.ajax({
            type:'GET',
            url: '/sell/demo1/'+ product,
            //contentType: 'application/json',
            success: function (result) {


                $("#sprice").val(result);
                var qtdSelling = parseInt($('#qtdSelling').val());
                var sellingPrice = parseFloat($('#sprice').val());
                var productName = $('#selectProd :selected').text();
                var discount = ((parseFloat($('#discount').val())*sellingPrice)/parseInt(100))*qtdSelling;
                var sellingTotal = (qtdSelling*sellingPrice)-discount;

                $("#totProd").val(sellingTotal.toFixed(2));
            }
        });

    });


    //$(calculateSum);

    function calculateSum() {

        var sum = 0;
        //iterate through each td based on class and add the values
        $(".itemTot").each(function() {

            var value = $(this).text();
            // add only if the value is number
            if(!isNaN(value) && value.length != 0) {
                sum += parseFloat(value);
            }

        });
        $('#sellingTotal').val(sum);
    };


   /* var sell = $('#sell').val();
    var total = $('#sellingTotal').val();
    $.ajax({
        type:'GET',
        url: '/sell/total/'+ sell + '/' + total,
        //contentType: 'application/json',
        success: function (result) {


            $("#sprice").val(result);
            var qtdSelling = parseInt($('#qtdSelling').val());
            var sellingPrice = parseFloat($('#sprice').val());
            var productName = $('#selectProd :selected').text();
            var discount = ((parseFloat($('#discount').val())*sellingPrice)/parseInt(100))*qtdSelling;
            var sellingTotal = (qtdSelling*sellingPrice)-discount;

            $("#totProd").val(sellingTotal.toFixed(2));
        }
    });*/




});











