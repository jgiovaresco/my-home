import React from 'react';
import type { Story, Meta } from '@storybook/react';
import { MemoryRouter } from 'react-router-dom';

import { NavigationProps, Navigation, NavItem } from './navigation';

export default {
  title: 'Components/Navigation',
  component: Navigation,
  decorators: [
    (story) => (
      <MemoryRouter initialEntries={['/', '/about', '/library']}>
        {story()}
      </MemoryRouter>
    ),
  ],
} as Meta;

const Template: Story<NavigationProps> = (args) => <Navigation {...args} />;

const navItems: NavItem[] = [
  { path: '/', label: 'Home' },
  { path: '/library', label: 'Library', secured: true },
  { path: '/about', label: 'About', secured: true },
];

export const Default = Template.bind({});
Default.args = {
  navItems,
};

export const Authenticated = Template.bind({});
Authenticated.args = {
  navItems,
  isAuthenticated: true,
};
