function validateUrl(urlInput) {
    const urlPattern = /^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([/\w .-]*)*\/?$/;
    if (!urlPattern.test(urlInput)) {
        alert('Please enter a valid URL.');
        return false;
    }
    return true;
}