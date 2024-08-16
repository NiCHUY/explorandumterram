const userId = sessionStorage.getItem('userId');

if (!userId) {
        window.location.href = 'login';
}