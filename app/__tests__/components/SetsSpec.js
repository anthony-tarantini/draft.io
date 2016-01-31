jest.dontMock('../../src/components/Sets');

import React from 'react';
import utils from 'react-addons-test-utils';
import sd from 'skin-deep';

const Sets = require('../../src/components/Sets');
const Client = require('../../src/clients/BaseApiClient');


describe('Sets', () => {
    var tree, instance, vdom;
    beforeEach(() => {
        tree = sd.shallowRender(<Sets />);
        instance = tree.getMountedInstance();
        vdom = tree.getRenderOutput();
    });

    it('should have a select box', () => {
        expect(vdom.type).toBe('select');
    });

    it('should call sets endpoint on component mounting', () => {
        Client.doFetch.mockReturnValueOnce({
            then: () => {
            }
        });
        instance.componentDidMount();
        expect(Client.doFetch).toBeCalled();
    });

    it('should set options to be data received from sets endpoint', () => {
        Client.doFetch.mockReturnValueOnce({
            then: (cb) => {
                cb({sets: [{name: "setone", code:"ST1"}, {name:"settwo", code:"ST2"}]})
            }
        });
        instance.componentDidMount();

        var reRendered = tree.getRenderOutput();
        expect(reRendered.props.children[0]).toEqual(<option key='0' value="setone">setone</option>);
        expect(reRendered.props.children[1]).toEqual(<option key='1' value="settwo">settwo</option>);
    });
});
