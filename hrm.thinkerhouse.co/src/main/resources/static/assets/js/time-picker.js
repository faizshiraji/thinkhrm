document.addEventListener('DOMContentLoaded', function () {
            function populateTimeSelect(selectElement) {
                for (let hour = 0; hour < 24; hour++) {
                    const option = document.createElement('option');
                    const timeString = (hour < 10 ? '0' : '') + hour + ':00:00';
                    option.value = timeString;
                    option.text = timeString;
                    selectElement.appendChild(option);
                }
            }

            function populateBreakTimeSelect(selectElement) {
                for (let minutes = 5; minutes <= 60; minutes += 5) {
                    const option = document.createElement('option');
                    const timeString = '00:' + (minutes < 10 ? '0' : '') + minutes + ':00';
                    option.value = timeString;
                    option.text = timeString;
                    selectElement.appendChild(option);
                }
            }

            const startTimeSelect = document.getElementById('start-time');
            const endTimeSelect = document.getElementById('end-time');
            const breakTimeSelect = document.getElementById('break-time');

            populateTimeSelect(startTimeSelect);
            populateTimeSelect(endTimeSelect);
            populateBreakTimeSelect(breakTimeSelect);
        });