import React from 'react';
import type { Story, Meta } from '@storybook/react';
import { action } from '@storybook/addon-actions';

import { TextInput, TextInputProps } from './text-input';

export default {
  title: 'Components/Form/TextInput',
  component: TextInput,
} as Meta;

const Template: Story<TextInputProps> = (args) => <TextInput {...args} />;

export const Default = Template.bind({});
Default.args = {
  label: 'My label',
  value: 'a value',
  onChange: action('onChange'),
};

export const WithoutLabel = Template.bind({});
WithoutLabel.args = {
  value: 'a value',
  onChange: action('onChange'),
};
