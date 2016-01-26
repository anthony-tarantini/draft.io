import React from 'react';
import Sets from './Sets';

class App extends React.Component {

    render() {
        return (
            <div>
                <header>
                    <h1 className="application-name">Draft.io</h1>
                </header>
                <Sets />
                <button className="draft-button">Draft!</button>
            </div>
        )
    }
}

module.exports = App;