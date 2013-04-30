###
JS - User
Date of creation : 26/04/2013
Creator : brnrd
target : modal, submit form
###

context = type: null, url: null

# Modal handler
setContext = (type, url) ->
        context.type = type
        context.url = url
        
# Modal handler
modalActionHandler = () ->
  if context.type is "modify"
    $('#modal-user .modal-header h3').text('Modify your account')
    $('#modal-user .modal-body').load(context.url + ' #modify')
    $('#modal-user .modal-footer #submit').html('Save')
    $('#modal-user .modal-footer #submit').removeClass('btn-danger').addClass('btn-success')
  else
    $('#modal-user .modal-header h3').text('Delete your account')
    $('#modal-user .modal-body').load(context.url + ' #delete')
    $('#modal-user .modal-footer #submit').html('Delete')
    $('#modal-user .modal-footer #submit').removeClass('btn-success').addClass('btn-danger')
  
  $('#modal-user').modal('show')
  
# Modify handler
modifyHandler = (dataToSend) ->
  $.post '/s/account/update',
  dataToSend
  (data) ->
    $('#modal-user').modal('hide')

# Delete handler
deleteHandler = (dataToSend) ->
  $.post '/s/account/delete',
    dataToSend
    (data) ->
      $('#modal-user').modal('hide')


# Modify user click to open modal
$('#modify-user').on "click", (event) ->
  setContext('modify', '/home-rental/s/account/modal/')
  console.log context
  modalActionHandler()

# Delete user clieck to open modal
$('#delete-user').on "click", (event) ->
  setContext('delete', '/home-rental/s/account/modal/')
  modalActionHandler()
  
# Submit button click to submit form
$('#submit').on "click", (event) ->
  if ($('#submit').hasClass('btn-success'))
    $('#modify-form').submit()
  else
    $('#delete-form').submit()

# Modify form submit
$('#modify-form').on "submit", (event) ->
  event.preventDefault()
  modifyHandler($(this).serialize())

# Delete form submit
$('#delete-form').on "submit", (event) ->
  console.log ('DELETE FORM SUBMIT')
  event.preventDefault()
  deleteHandler($(this).serialize())