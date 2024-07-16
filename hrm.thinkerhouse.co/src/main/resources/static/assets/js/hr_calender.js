document.addEventListener('DOMContentLoaded', function() {
    const yearDropdown = document.getElementById('yearDropdown');
    const monthDropdown = document.getElementById('monthDropdown');
    const yearDropdownButton = document.getElementById('yearDropdownButton');
    const monthDropdownButton = document.getElementById('monthDropdownButton');

    yearDropdown.addEventListener('click', function(e) {
        if (e.target.tagName === 'A') {
            const selectedText = e.target.getAttribute('data-value');
            yearDropdownButton.textContent = selectedText;
        }
    });

    monthDropdown.addEventListener('click', function(e) {
        if (e.target.tagName === 'A') {
            const selectedText = e.target.getAttribute('data-value');
            monthDropdownButton.textContent = selectedText;
        }
    });
});