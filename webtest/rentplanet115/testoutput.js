function editformSubmit() {
    var form = document.getElementById('editform');
    elements = form.elements;

    alertForm(form);
    elements['address'].value += elements['formradio'].value;

    return false;
}

function alertForm(form) {
    var elements = form.elements;
    var alertStr = '';
    var element, type;
    for (var i = 0; i < elements.length; i++) {
        element = elements[i];
        type = element.type;
        alertStr += type + '/' + element.name + ':' + element.value;
        if (type == 'radio')
            alertStr += ':' + element.checked;
        alertStr += '\n';
    }
    alert(alertStr);
}
