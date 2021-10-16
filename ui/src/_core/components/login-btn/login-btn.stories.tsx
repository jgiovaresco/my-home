import React from 'react';
import type { Story, Meta } from '@storybook/react';

import { LoginButton, LoginButtonProps } from './login-btn';

export default {
  title: 'Components/LoginButton',
  component: LoginButton,
} as Meta;

const Template: Story<LoginButtonProps> = (args) => <LoginButton {...args} />;

export const NotAuthenticated = Template.bind({});
NotAuthenticated.args = { isAuthenticated: false };

export const Authenticated = Template.bind({});
Authenticated.args = { isAuthenticated: true };
