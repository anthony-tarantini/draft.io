module.exports = {
    doFetch(path, method, data) {
        return new Promise(function (resolve) {
            var url = `http://localhost:8080/${path}`;
            var opts = {
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                credentials: 'include',
                method: method || 'GET'
            };
            if (!!data) {
                opts.body = JSON.stringify(data);
            }
            fetch(url, opts)
                .then(r=>r.json())
                .then(data=>resolve(data));
        });
    }
};