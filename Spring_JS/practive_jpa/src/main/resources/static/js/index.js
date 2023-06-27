$(document).ready(function() {
    let checkboxes = $('input[type="radio"][name="topic"]');
    checkboxes.on('click', filterCourses);
    function filterCourses() {
        let selectedTopics = [];
        checkboxes.each(function() {
            if ($(this).is(':checked')) {
                selectedTopics.push($(this).val());
            }
        });
        let courseItems = $('.course-item');
        courseItems.each(function() {
            let courseTopic = $(this).find('.description').text();
            if (selectedTopics.length === 0 || selectedTopics.includes(courseTopic)) {
                $(this).show();
            } else {
                $(this).hide()
            }
        });
    }
});