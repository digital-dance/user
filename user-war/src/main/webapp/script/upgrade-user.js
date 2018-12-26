var NO_COMPLETE_MESSAGE = "您所填写的用户信息不完整";
var NO_COMPLETE_CAR_MESSAGE = "您所填写的家用汽车信息不完整";
var AGE_MUST_BE_NUMBER = "年龄必须是数字";
var NAME_MUST_BE_CHINESE = "姓名必须是中文";
var PLATE_NUMBER_INVALID = "您所填写的牌照号码不正确";
var CONFIRM_UPGRADE_WITHOUT_VIP = "您已完成白金用户的资料，进一步完善个人资料可以升级至VIP用户。\n按“确定”升级到白金，按“取消”继续完善资料。"
var cars = new Array();

$(document).ready(function () {
    LoadUserBasicInfo();

    LoadSelectOptions();

    loadCarYearsOptions();

    //loadOptionsAndUserInfo();

    $("#btnUpgrade").click(function () {
        SavePersonInfo();
    });

});

function loadOptionsAndUserInfo() {
    $.post("RequestPage/SystemDictionary.aspx", { typeCode: 'marital' },
            function (data, status) {
                $("#selectMarried").html(data);
                LoadUserBasicInfo();
            }
            );
}

function loadCarYearsOptions()
{
    var optionsYear = "<option value=''>- 请选择 -</option>";
    for (i = 2014; i >= 1984; i--) {
        optionsYear += "<option values='" + i + "'>" + i + "</option>";
    }
    $('#selectYear').html(optionsYear);
    var optionsMonth = "<option value=''>- 请选择 -</option>";
    for (i = 1; i <= 12; i++) {
        optionsMonth += "<option values='" + i + "'>" + i + "</option>";
    }
    $('#selectMonth').html(optionsMonth);
}

function LoadUserBasicInfo() {
    $.post("Admin/Users/UserInfo.aspx", { id: 'all' },
                    function (result, status) {
                        var objs = result.split('|||');
                        var userLevel = objs[2];
                        $("#hiddenUserLevel").attr('value', objs[2]);
                        if (userLevel == '普通') {
                            $("#divGold").show();
                            $("#hrUpgrade").show();
                            $("#divVip").show();
                        }
                        if (userLevel == '白金') {
                            $("#hrUpgrade").show();
                            $("#divVip").show();
                        }
                    }
                    );
}

function LoadSelectOptions() {
    $.post("RequestPage/SystemDictionary.aspx", { typeCode: 'marital|VehicleModel' },
            function (data, status) {
                var result = data.split("||");
                $("#divMarried").html(result[0]);
                $("#radioMarried0").attr("checked", true);
                var carInfo = result[2];
                var carStringArray = carInfo.split(";");
                cars = new Array();
                for (var i = 0; i < carStringArray.length; i++) {
                    if (carStringArray[i] != null) {
                        var carString = carStringArray[i].split("|");
                        var car = {
                            ID: carString[0],
                            Name: carString[1],
                            ParentID: carString[2]
                        };
                        cars[i] = car;
                    }
                }

                GenerateBrand();

                $("#selectBrand").change(function () {
                    var parentID = $("#selectBrand").val();
                    GenerateType(parentID, false);
                });
            }
            );
}

function GenerateBrand() {
    var brandHtml = '<option value="">- 请选择 -</option>';
    for (var i = 0; i < cars.length; i++) {
        if (cars[i].ParentID == "0" || cars[i].ParentID == "") {
            brandHtml = brandHtml + '<option value="' + cars[i].ID + '">' + cars[i].Name + '</option>';
        }
    }
    $("#selectBrand").html(brandHtml);
}

function GenerateType(parentID, isUpgrade) {
    var typeHtml = '<option value="">- 请选择 -</option>';
    for (var i = 0; i < cars.length; i++) {
        if (cars[i].ParentID == parentID && parentID != "") {
            typeHtml = typeHtml + '<option value="' + cars[i].ID + '">' + cars[i].Name + '</option>';
        }
    }
    $("#selectType").html(typeHtml);
}

function checkCompleteSaveGold() {
    var name = $.trim($("#txtUpgradeName").val());
    var isValid = true;
    if (name == "") {
        isValid = false;
    }
    var sex = jQuery("#selectSex").val();
    if (sex == "") {
        isValid = false;
    }
    var age = $.trim($("#txtAge").val());
    if (age == "") {
        isValid = false;
    }
    var married = jQuery("#selectMarried").val();
    if (married == "") {
        isValid = false;
    };
    var profession = jQuery("#selectProfession").val();
    if (profession == "") {
        isValid = false;
    };
    return isValid;
}

function checkCompleteSaveVip() {
    var brand = jQuery("#selectBrand").val();
    var type = jQuery("#selectType").val();
    var isValid = true;
    if (brand == "" || type == "") {
        isValid = false;
    }
    var plateNumber = $.trim($("#txtPlateNumber").val());
    if (plateNumber == "") {
        isValid = false;
    }
    var month = jQuery("#selectMonth").val();
    var year = jQuery("#selectYear").val();
    if (month == "" || year == "") {
        isValid = false;
    }
    return isValid;
}

function checkValidPlateNumber(plateNumber) {
    var expPlateNumber = new RegExp("^[a-zA-Z]{1}[-]?[a-z0-9A-Z]{5}$");
    //var plateNumber = jQuery("#txtPlateNumberU").val();
    var isValidPlateNumber = true;
    if (plateNumber != "" && plateNumber.match(expPlateNumber) == null) {
        isValidPlateNumber = false;
    }
    return isValidPlateNumber;
}

function checkValidSaveAge() {
    var age = jQuery("#txtAge").val();
    return !isNaN(age);
}

function isChn(str) {
    var reg = /^[\u4E00-\u9FA5]+$/;
    if (str != "" && !reg.test(str)) {
        return false;
    }
    return true;
}

function SavePersonInfo()
{
    var errorMessage = "";

    var isCompleteGold = checkCompleteSaveGold();
    var isCompleteVip = checkCompleteSaveVip();
    var isValidAge = checkValidSaveAge();
    var isValidPlateNumber = checkValidPlateNumber($("#txtPlateNumber").val());
    var isValidChinese = isChn($.trim($("#txtUpgradeName").val()));
    var isValidGold = isCompleteGold && isValidAge;
    var isValidVip = isCompleteVip && isValidPlateNumber;

    var name = $.trim($("#txtUpgradeName").val());
    var age = $.trim($("#txtAge").val());
    var sex = $("input[name='selectSex']:checked").val();
    var married = $("input[name='selectMarried']:checked").val();
    var profession = jQuery("#selectProfession").val();
    var plateNumber = jQuery("#selectRegion").val() + $.trim($("#txtPlateNumber").val());
    var brand = jQuery("#selectBrand").val();
    var type = jQuery("#selectType").val();
    var month = jQuery("#selectMonth").val();
    var year = jQuery("#selectYear").val();

    ///普通用户验证
    var level = $("#hiddenUserLevel").val();
    if (level == '普通')
    {
        if (isCompleteGold == false)
        {
            errorMessage += NO_COMPLETE_MESSAGE;
        }
        if (isValidChinese == false) {
            errorMessage += "\n" + NAME_MUST_BE_CHINESE;
        }
        if (isValidAge == false) {
            errorMessage += "\n" + AGE_MUST_BE_NUMBER;
        }
        
        if (errorMessage != "")
        {
            alert(errorMessage);
            return;
        }

        //Vip信息不完整，确认只升级Vip
        if (isCompleteVip == false || isValidPlateNumber == false) {
            var confirmMessage = CONFIRM_UPGRADE_WITHOUT_VIP;
            if (isValidPlateNumber == false && $("#txtPlateNumber").val() != "") {
                confirmMessage = PLATE_NUMBER_INVALID + "\n" + confirmMessage;
            }
            var isConfirmed = confirm(confirmMessage);
            if (!isConfirmed) {
                return;
            }
        }
    }

    if (level == '白金')
    {
        if (isCompleteVip == false) {
            errorMessage += NO_COMPLETE_CAR_MESSAGE;
        }
        if (isValidPlateNumber == false) {
            errorMessage += "\n" + PLATE_NUMBER_INVALID;
        }
        if (errorMessage != "") {
            alert(errorMessage);
            return;
        }
        //把isValidGold设为false,后台可以只处理Vip的信息
        isValidGold = false;
    }

    $.post("Admin/Users/UpgradeUser.aspx",
                {
                    name: name,
                    age: age,
                    sex: sex,
                    married: married,
                    profession: profession,
                    plateNumber: plateNumber,
                    brand: brand,
                    type: type,
                    month: month,
                    year: year,
                    isValidGold: isValidGold,
                    isValidVip: isValidVip,
                    isUpgrade : true
                },
                function (data, status) {
                    var objs = data.split('|||');
                    var result = objs[0];
                    switch (result) {
                        case "0":
                            var userLevel = objs[1];
                            if (userLevel == "1")
                                alert("恭喜成功升级为美亚道路安全行白金用户！");
                            else
                                alert("恭喜成功升级为美亚道路安全行VIP用户！");
                            break;
                        case "-1":
                            alert("升级失败！");
                            break;
                    }
                    window.location.href = "user.html";
                });
}