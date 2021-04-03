import React, { useState } from 'react';
import type { Book } from '../../model';
import { Button, TextInput } from '../../../_core/components';
import './add-book-form.scss';

export interface AddBookFormProps {
  onSubmit: (newBook: Book) => void;
}

export const AddBookForm: React.FC<AddBookFormProps> = ({ onSubmit }) => {
  const [isbn, setIsbn] = useState('');
  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const newBook = { title, isbn, author };
    onSubmit(newBook);
  };

  return (
    <form className="add-book-form" onSubmit={handleSubmit}>
      <TextInput name="isbn" label="Isbn" value={isbn} onChange={setIsbn} />
      <TextInput name="title" label="Title" value={title} onChange={setTitle} />
      <TextInput
        name="author"
        label="Author"
        value={author}
        onChange={setAuthor}
      />

      <section className="actions">
        <Button type="submit" label="Add book" primary />
      </section>
    </form>
  );
};
