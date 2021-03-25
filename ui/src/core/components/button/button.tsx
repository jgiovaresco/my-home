import React from 'react';
import './button.scss';

export interface ButtonProps {
  /** type of the button */
  type?: 'button' | 'submit' | 'reset';

  /** used to disable the action */
  disabled?: boolean;

  /** Is this the principal call to action on the page? */
  primary?: boolean;

  /** used to draw special attention to the button (e.g. destructive actions) */
  accent?: boolean;

  /** Button contents */
  label: string;

  /** Optional click handler */
  onClick?: () => void;
}

export const Button: React.FC<ButtonProps> = ({
  type = 'button',
  primary = false,
  accent = false,
  label,
  ...props
}) => {
  const mode = primary
    ? 'btn--primary'
    : accent
    ? 'btn--accent'
    : 'btn--secondary';
  return (
    <button type={type} className={['btn', mode].join(' ')} {...props}>
      {label}
    </button>
  );
};
