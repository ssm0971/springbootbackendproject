$(document).ready(function () {
    // 전체 선택 체크박스 클릭 시 그룹의 모든 체크박스 선택/해제
    $('#allSelect').click(function () {
        var isChecked = $(this).is(':checked');
        $('.checkbox').prop('checked', isChecked);
    });

    // 각 개별 체크박스 해제 시 전체 선택 체크박스도 해제
    $('.checkbox').change(function () {
        var allChecked = $('.checkbox:checked').length === $('.checkbox').length;
        $('#allSelect').prop('checked', allChecked);
    });
    
});
