$(document).ready(function(){
        // Initialize the date pickers
        $('#datepicker-from').datepicker({
          format: 'mm/dd/yyyy',
          autoclose: true,
        }).on('changeDate', function(selected) {
          var startDate = new Date(selected.date.valueOf());
          $('#datepicker-to').datepicker('setStartDate', startDate);
        });
      
        $('#datepicker-to').datepicker({
          format: 'mm/dd/yyyy',
          autoclose: true,
        }).on('changeDate', function(selected) {
          var endDate = new Date(selected.date.valueOf());
          $('#datepicker-from').datepicker('setEndDate', endDate);
        });
      });