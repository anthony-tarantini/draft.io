jest.dontMock('../../src/components/App');

import React from 'react';
import TestUtils from 'react-addons-test-utils';

const App = require('../../src/components/App');
const Sets = require('../../src/components/Sets');

describe('App', () => {
    it('should have a header with app title, set selection, and button', () => {
        const shallowRenderer = TestUtils.createRenderer();
        shallowRenderer.render(<App />);
        const app = shallowRenderer.getRenderOutput();
        var header = app.props.children[0];
        var selection = app.props.children[1];
        var button = app.props.children[2];
        var title = header.props.children;

        expect(header.type).toBe('header');
        expect(title.type).toBe('h1');
        expect(title.props.children).toBe('Draft.io');
        expect(selection).toEqual(<Sets />);
        expect(button.type).toBe('button');
        expect(button.props.children).toBe('Draft!');
    });

});
