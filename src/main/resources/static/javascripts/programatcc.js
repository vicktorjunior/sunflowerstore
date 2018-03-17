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
    /*$('#qtdSelling').change(function () {
        var qtdSelling = parseInt($('#qtdSelling').val());
        var sellingPrice = parseFloat($('#sellingPrice').val());
        $('#sellingTotal').val(qtdSelling*sellingPrice);
    });
*/

    $(document).ready(function () {
        $('#desconto').val(0);
    });


    $('#addrow').click(function () {
        var qtdSelling = parseInt($('#qtdSelling').val());
        var sellingPrice = parseFloat($('#sellingPrice').val());
        var productName = $('#products :selected').text();
        var discount = ((parseFloat($('#desconto').val())*sellingPrice)/100)*qtdSelling;
        var sellingTotal = (qtdSelling*sellingPrice)-discount;
        //console.log(discount);
        //console.log(sellingTotal);


        var chtml = "<tr>\n" +
            "                        <td> " + qtdSelling + "</td>\n" +
            "                        <td> " + productName + "</td>\n" +
            "                        <td>" + sellingPrice +" </td>\n" +
            "                        <td>" + discount.toFixed(2) +" </td>\n" +
            "                        <td class=\"sellingTotal\">" + sellingTotal.toFixed(2) +" </td>\n" +
            "                        <td><div class=\"col-sm-4\"><input type=\"button\" id=\"delete\" class=\"form-control btn btn-danger\" value=\"Delete\"/></div></td>\n" +
            "                    </tr>";

        $('#productsToSell').find('> tbody').append(chtml);


        /*$(".delete").click(function() {
            $(this).parents("tr").remove();
        });*/

        $('#qtdSelling').val(1);
        $('#products').val(0);
        $('#desconto').val(0);
        $('#sellingPrice').val(0);




    });


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






    $('#ccnt').data('count', 0);
    /*$('#addrow').click(function () {

        function getcount() {
            var $this = $('#ccnt'),
                count = $this.data('count') + 1;

            $this.data('count', count);
            return count;
        }

        var mycount = getcount();
        //console.log('mycount: ' + mycount);

        var tdclass;
        if (mycount % 2 == 1) {
            tdclass = "td1";
        } else {
            tdclass = "td2";
        }
        //console.log('tdclass: ' + tdclass);
        //window.alert(mycount + ' ' + tdclass);

        var chtml = "<tr>\n" +
            "                        <th scope=\"row\">1</th>\n" +
            "                        <td></td>\n" +
            "                        <td></td>\n" +
            "                        <td>1.20</td>\n" +
            "                        <td><div class=\"col-sm-4\"><input type=\"button\" id=\"delete\" class=\"form-control btn btn-danger\" value=\"Delete\"/></div></td>\n" +
            "                    </tr>";


        //console.log('chtml is: ' + chtml);
        //window.alert(chtml);
        //console.log("Writing an iteration of chtml to scrren...");
        $('#productsToSell').find('> tbody').append(chtml);

        $(".delete").click(function() {
            $(this).parents("tr").remove();
        });
    });*/















