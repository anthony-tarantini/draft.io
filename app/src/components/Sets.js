import React from 'react';

const Client = require('../clients/BaseApiClient');

class Sets extends React.Component {
    constructor() {
        super();
        this.state = {
            sets: []
        }
    }

    render() {
        return (
            <select>
                {this.renderOptions()}
            </select>
        )
    }

    renderOptions() {
        let options = [];
        this.state.sets.forEach((set, i) => {
            options.push(<option key={i} value={set.name}>{set.name}</option>)
        });
        return options;
    }

    componentDidMount() {
        Client.doFetch('sets').then((data) => {
            this.setState({
                sets: data.sets
            })
        });
    }
}

module.exports = Sets;
