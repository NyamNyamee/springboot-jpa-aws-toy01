const header = {
    init: function () {  // 화살표함수가 아닌 function 일반함수를 사용하는 이유는, this의 스코프가 다르기 때문
        document.getElementById('header-title').addEventListener('click', this.headerTitleClickEventHandler)
    },

    headerTitleClickEventHandler: function () {
        window.location.href = "/";
    }
};

header.init();