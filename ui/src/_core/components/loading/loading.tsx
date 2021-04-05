import React from 'react';
import './loading.scss';

export interface LoadingProps {}

export const Loading: React.FC<LoadingProps> = ({}) => (
  <div className="loading">
    <div className="loading__content">
      <span className="spinner" />
      <span className="message">Loading...</span>
    </div>
  </div>
);
