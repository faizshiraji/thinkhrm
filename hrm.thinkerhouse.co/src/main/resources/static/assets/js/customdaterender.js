$(document).ready(function () {
    // Holidays will be dynamically populated from Thymeleaf
    const holidays = /*[[${holidays}]]*/ [];

    const currentDate = new Date();
    let currentMonth = currentDate.getMonth();
    let currentYear = currentDate.getFullYear();
    const yearRange = 10;

    function populateYearSelect() {
        const yearSelect = $('#yearSelect');
        yearSelect.empty();
        for (let i = currentYear - yearRange; i <= currentYear + yearRange; i++) {
            yearSelect.append(`<option value="${i}">${i}</option>`);
        }
    }

    function updateCalendar() {
        $('#monthSelect').val(currentMonth);
        $('#yearSelect').val(currentYear);
        renderCalendar(currentMonth, currentYear);
    }

    function renderCalendar(month, year) {
        const firstDay = new Date(year, month, 1).getDay();
        const daysInMonth = new Date(year, month + 1, 0).getDate();
        const calendarBody = $('#calendarBody');
        calendarBody.empty();

        console.log("Rendering calendar for month:", month, "year:", year);
        console.log("Holidays:", holidays);

        let date = 1;
        for (let i = 0; i < 6; i++) {
            let row = '<tr>';
            for (let j = 0; j < 7; j++) {
                if (i === 0 && j < firstDay) {
                    row += '<td></td>';
                } else if (date > daysInMonth) {
                    break;
                } else {
                    const dateString = `${year}-${String(month + 1).padStart(2, '0')}-${String(date).padStart(2, '0')}`;
                    let holidayClass = '';
                    let additionalClass = '';

                    const holiday = holidays.find(h => new Date(h.date).toISOString().split('T')[0] === dateString);
                    if (holiday) {
                        holidayClass = holiday.description.includes("Government") ? 'govt-holiday' : 'holiday';
                        console.log("Found Holiday:", holiday);
                    }

                    if (j === 5) { // Friday
                        additionalClass = 'friday';
                    }

                    row += `<td><button class="date-btn ${holidayClass} ${additionalClass}" data-date="${dateString}">${date}</button></td>`;
                    date++;
                }
            }
            row += '</tr>';
            calendarBody.append(row);
        }
    }

    $('#monthSelect').change(function () {
        currentMonth = parseInt($(this).val());
        updateCalendar();
    });

    $('#yearSelect').change(function () {
        currentYear = parseInt($(this).val());
        updateCalendar();
    });

    $('#prevMonth').click(function () {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        updateCalendar();
    });

    $('#nextMonth').click(function () {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        updateCalendar();
    });

    $(document).on('click', '.date-btn', function () {
        const date = $(this).data('date');
        alert(`You clicked on ${date}`);
        // Add your custom functionality here
    });

    // Initialize the calendar
    populateYearSelect();
    updateCalendar();
});