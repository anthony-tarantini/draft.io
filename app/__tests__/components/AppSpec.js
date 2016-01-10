jest.dontMock('../../src/components/App');

import React from 'react';
import TestUtils from 'react-addons-test-utils';
const App = require('../../src/components/App');

describe('App', () => {
    const shallowRenderer = TestUtils.createRenderer();
    shallowRenderer.render(<App />);
    const app = shallowRenderer.getRenderOutput();

    it('should have a div as container', () => {
        expect(app.type).toBe('div');
    });

});
