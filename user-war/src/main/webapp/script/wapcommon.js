var equalsRegEx = new RegExp('([=])', 'ig');
var equalsDeRegEx = new RegExp('(char[\(]61[\)])', 'ig');
var questionMarkRegEx = new RegExp('([\?])', 'ig');
var questionMarkDeRegEx = new RegExp('(char[\(]63[\)])', 'ig');
var andMarkRegEx = new RegExp('([\&])', 'ig');
var andMarkDeRegEx = new RegExp('(char[\(]38[\)])', 'ig');
var backslashRegEx = new RegExp('([\/])', 'ig');
var backslashDeRegEx = new RegExp('(char[\(]47[\)])', 'ig');

function trimStart(str, chars) {
    var result = str;
    try {
        var trimRegEx = new RegExp('^([' + chars + ']+)', 'ig');
        result = str.replace(trimRegEx, '');
    }
    catch (e)
    { }
    return result;
}

function trimEnd(str, chars) {
    var result = str;
    try {
        var trimRegEx = new RegExp('([' + chars + ']+)$', 'ig');
        result = str.replace(trimRegEx, '');
    }
    catch (e) {     
    }
    return result;
}

function trimStartAndEnd(str, chars) {
    var result = str;
    try
    {
        var trimRegEx = new RegExp('(^([' + chars + ']+))|(([' + chars + ']+)$)', 'ig');
        result = str.replace(trimRegEx, '');
    }
        catch (e) {
    }
    return result;
}

function getQueryStringValue(key) {
    var regEx = new RegExp('[\?\&\#]+' + key + '=[^\&\#\?]*', 'ig');
    var separateRegEx = new RegExp('([^=]*)$', 'ig');
    //var idQueryString = document.URL.split('?');
    var queryString = '';
    var queryStringValue = '';
    try {
        //alert(document.URL);
        queryString = decodeURI(document.URL).match(regEx);
        //alert(idQueryString[0]);
        if (queryString != null && queryString.length > 0) {
            queryStringValue = queryString[0].match(separateRegEx)[0];
        }
        //$('#currentActivityId').val(idQueryString);
    }
    catch (ep) {
        alert(ep.Message);
    }
    //alert(queryStringValue);
    return queryStringValue;
}

function wapEncode(urlStr) {
    urlStr = urlStr.replace(equalsRegEx, 'char(61)')
    .replace(questionMarkRegEx, 'char(63)')
    .replace(andMarkRegEx, 'char(38)')
    .replace(backslashRegEx, 'char(47)');
    //alert(urlStr);
    return urlStr;
}

function wapDecode(urlStr) {
    urlStr = urlStr.replace(equalsDeRegEx, '=')
    .replace(questionMarkDeRegEx, '?')
    .replace(andMarkDeRegEx, '&')
    .replace(backslashDeRegEx, '/');
    //alert(urlStr);
    return urlStr;
}