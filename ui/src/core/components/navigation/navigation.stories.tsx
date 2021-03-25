import React from 'react';
import type { Story, Meta } from '@storybook/react';
import { MemoryRouter } from 'react-router-dom';

import { NavigationProps, Navigation } from './navigation';

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

export const Default = Template.bind({});
Default.args = {
  navItems: [
    { path: '/', label: 'Home' },
    { path: '/library', label: 'Library' },
    { path: '/about', label: 'About' },
  ],
};
