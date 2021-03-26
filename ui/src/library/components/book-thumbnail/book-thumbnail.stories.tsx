import React from 'react';
import type { Story, Meta } from '@storybook/react';

import { BookThumbnailProps, BookThumbnail } from './book-thumbnail';

export default {
  title: 'Library/BookThumbnail',
  component: BookThumbnail,
} as Meta;

const Template: Story<BookThumbnailProps> = (args) => (
  <BookThumbnail {...args} />
);

export const Default = Template.bind({});
Default.args = {
  book: {
    isbn: '9781617295621',
    title: 'Vert.x in Action',
    author: 'Julien Ponge',
  },
};
