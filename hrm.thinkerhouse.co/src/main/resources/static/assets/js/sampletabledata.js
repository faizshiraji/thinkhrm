$(document).ready(function() {
            // Sample data for the table
            const rows = [
                { id: 1, name: "Herman Beck", progress: 25, amount: "$77.99", deadline: "May 15, 2015", progressClass: "bg-success" },
                { id: 2, name: "Messsy Adam", progress: 75, amount: "$245.30", deadline: "July 1, 2015", progressClass: "bg-danger" },
                { id: 3, name: "John Richards", progress: 90, amount: "$138.00", deadline: "Apr 12, 2015", progressClass: "bg-warning" },
                { id: 4, name: "Peter Meggik", progress: 50, amount: "$77.99", deadline: "May 15, 2015", progressClass: "bg-primary" },
                { id: 5, name: "Edward", progress: 35, amount: "$160.25", deadline: "May 03, 2015", progressClass: "bg-danger" },
                { id: 6, name: "John Doe", progress: 65, amount: "$123.21", deadline: "April 05, 2015", progressClass: "bg-info" },
                { id: 7, name: "Henry Tom", progress: 20, amount: "$150.00", deadline: "June 16, 2015", progressClass: "bg-warning" },
                { id: 8, name: "Jane Smith", progress: 85, amount: "$200.00", deadline: "June 20, 2015", progressClass: "bg-success" },
                { id: 9, name: "Tom Hardy", progress: 40, amount: "$300.00", deadline: "July 15, 2015", progressClass: "bg-primary" },
                { id: 10, name: "Lucy Brown", progress: 55, amount: "$450.50", deadline: "August 25, 2015", progressClass: "bg-info" },
                { id: 11, name: "Michael Scott", progress: 70, amount: "$320.00", deadline: "September 12, 2015", progressClass: "bg-danger" },
                { id: 12, name: "Dwight Schrute", progress: 95, amount: "$180.00", deadline: "October 1, 2015", progressClass: "bg-success" },
                { id: 13, name: "Pam Beesly", progress: 60, amount: "$150.00", deadline: "November 5, 2015", progressClass: "bg-warning" },
                { id: 14, name: "Jim Halpert", progress: 80, amount: "$275.00", deadline: "December 10, 2015", progressClass: "bg-primary" },
                { id: 15, name: "Andy Bernard", progress: 45, amount: "$325.00", deadline: "January 14, 2016", progressClass: "bg-danger" },
                { id: 16, name: "Angela Martin", progress: 30, amount: "$400.00", deadline: "February 19, 2016", progressClass: "bg-info" },
                { id: 17, name: "Kevin Malone", progress: 20, amount: "$500.00", deadline: "March 23, 2016", progressClass: "bg-warning" },
                { id: 18, name: "Oscar Martinez", progress: 90, amount: "$225.00", deadline: "April 18, 2016", progressClass: "bg-success" },
                { id: 19, name: "Stanley Hudson", progress: 35, amount: "$275.50", deadline: "May 27, 2016", progressClass: "bg-primary" },
                { id: 20, name: "Phyllis Vance", progress: 65, amount: "$375.00", deadline: "June 29, 2016", progressClass: "bg-info" }
            ];

            const rowsPerPage = 5;
            let currentPage = 1;

            function renderTable() {
                const startIndex = (currentPage - 1) * rowsPerPage;
                const endIndex = startIndex + rowsPerPage;
                const visibleRows = rows.slice(startIndex, endIndex);

                $('#tableBody').empty();

                visibleRows.forEach(row => {
                    $('#tableBody').append(`
                        <tr>
                            <td>${row.id}</td>
                            <td>${row.name}</td>
                            <td>
                                <div class="progress">
                                    <div class="progress-bar ${row.progressClass}" role="progressbar" style="width: ${row.progress}%" aria-valuenow="${row.progress}" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </td>
                            <td>${row.amount}</td>
                            <td>${row.deadline}</td>
                        </tr>
                    `);
                });
            }

            function updatePagination() {
                $('#pageNumber').text(currentPage);
            }

            $('#prevPage').click(function(e) {
                e.preventDefault();
                if (currentPage > 1) {
                    currentPage--;
                    renderTable();
                    updatePagination();
                }
            });

            $('#nextPage').click(function(e) {
                e.preventDefault();
                if (currentPage * rowsPerPage < rows.length) {
                    currentPage++;
                    renderTable();
                    updatePagination();
                }
            });

            // Initial render
            renderTable();
            updatePagination();
        });