import React from 'react';
import type { Story, Meta } from '@storybook/react';

import { BooksList, BooksListProps } from './books-list';
import { newBook } from '../../model';

export default {
  title: 'Library/BooksList',
  component: BooksList,
} as Meta;

const Template: Story<BooksListProps> = (args) => <BooksList {...args} />;

export const Default = Template.bind({});
Default.args = {
  books: [newBook(), newBook(), newBook(), newBook()],
};
