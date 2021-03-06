###
JS - User-properties
Date of creation : 28/04/2013
Creator : brnrd
target : modal, submit form
###

###########
#  Modal  #
###########

context = type: null, url: null, property_id:null

# Modal handler
setContext = (type, url, id) ->
        context.type = type
        context.url = url
        context.property_id = id

modalActionHandler = () ->
  if context.type is "modify"
    $('#modal-property .modal-header h3').text('Modify this property')
    $('#modal-property .modal-body').load(context.url + ' #modify')
    $('#modal-property .modal-footer #submit' ).html('Save')
    $('#modal-property .modal-footer #submit' ).attr('form','modify-form')
    $('#modal-property .modal-footer #submit' ).removeClass('btn-danger').addClass('btn-success')
  else
    $('#modal-property .modal-header h3').text('Delete this property')
    $('#modal-property .modal-body').load(context.url + ' #delete')
    $('#modal-property .modal-footer #submit' ).html('Delete')
    $('#modal-property .modal-footer #submit' ).attr('form','delete-form')
    $('#modal-property .modal-footer #submit' ).removeClass('btn-success').addClass('btn-danger')
  
  $('#modal-property').modal('show')
  
  
# Modify handler
modifyHandler = (dataToSend) ->
  $.post '/home-rental/s/property/' + context.property_id + '/update',
  dataToSend
  (data) ->
    $('#modal-property').modal('hide')

# Delete handler
deleteHandler = (dataToSend) ->
  $.post '/home-rental/s/property/' + context.property_id + '/delete',
    dataToSend
    (data) ->
      $('#modal-property').modal('hide')


# Modify user click to open modal
$('#properties-list #btn-modify').on "click", (event) ->
  id = $(this).parent('.item-action-btn').data("property-id")
  console.log id
  setContext('modify', '/home-rental/s/property/' + id + '/modal', id)
  modalActionHandler()

# Delete user clieck to open modal
$('#properties-list #btn-delete').on "click", (event) ->
  id = $(this).parent('.item-action-btn').data("property-id")
  setContext('delete', '/home-rental/s/property/' + id + '/modal', id)
  modalActionHandler()

# Modify form submit
$('#modify-form').on "submit", (event) ->
  event.preventDefault()
  modifyHandler($(this).serialize())

# Delete form submit
$('#delete-form').on "submit", (event) ->
  event.preventDefault()
  deleteHandler($(this).serialize())
  
  ##################
#   Datepicker   #
##################

ckeckin = null
target = null

formatDate = (date) ->
    res = date.toLocaleString().split(" ")[0]
    t_res = res.split("/")
    if t_res[0].length == 1
        return '0'+t_res[0]+"/"+t_res[1]+"/"+t_res[2]
    return t_res.join("/")

$('#modal-property #rentStart').datepicker(
    onClose: (selectedDate) ->
        console.log selectedDate
        checkin = new Date(selectedDate)
        console.log checkin
        target = new Date(checkin.getFullYear(), checkin.getMonth(), checkin.getDate()+7)
        console.log target
        $('#rentStop').datepicker('option', 'minDate', selectedDate)
        $('#rentStop').val(formatDate(target))
)

$('#modal-property #rentStop').datepicker(
    defaultDate: if target then target else ''
    onClose: (selectedDate) ->
        if checkin
            $('#rentStart').datepicker('option', 'maxDate', selectedDate)
)