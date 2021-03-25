import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import type { Story, Meta } from '@storybook/react';

import { Header, HeaderProps } from './header';

export default {
  title: 'Components/Header',
  component: Header,
} as Meta;

const Template: Story<HeaderProps> = (args) => (
  <Router>
    <Header {...args} />
  </Router>
);

export const Default = Template.bind({});
Default.args = {};

export const WithNavItems = Template.bind({});
WithNavItems.args = {
  navItems: [
    { path: '/', label: 'Home', active: true },
    { path: '/library', label: 'Library' },
    { path: '/about', label: 'About' },
  ],
};
