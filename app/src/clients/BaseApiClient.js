module.exports = {
    doFetch (path, method, data) {
        return new Promise(function (resolve, reject) {
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
                .then(r => {
                    if ([200, 201].indexOf(r.status) == -1) {
                        return r.json().then(data => {throw new Error(data)});
                    }
                    return r.json()
                })
                .then(data => resolve(data))
                .catch(error => reject(error));
        });
    }
};