import React from 'react';
import type { Story, Meta } from '@storybook/react';
import { action } from '@storybook/addon-actions';

import { AddBookFormProps, AddBookForm } from './add-book-form';

export default {
  title: 'Library/AddBookForm',
  component: AddBookForm,
} as Meta;

const Template: Story<AddBookFormProps> = (args) => <AddBookForm {...args} />;

export const Default = Template.bind({});
Default.args = {
  onSubmit: action('onSubmit'),
};
