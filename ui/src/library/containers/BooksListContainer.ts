import { connect } from 'react-redux';
import type { RootState } from '../../store';

import { getBooks } from '../selectors';
import { BooksList } from '../components';

const mapStateToPros = (state: RootState) => ({
  books: getBooks(state),
});
const dispatchProps = {};

export const BooksListContainer = connect(
  mapStateToPros,
  dispatchProps,
)(BooksList);
